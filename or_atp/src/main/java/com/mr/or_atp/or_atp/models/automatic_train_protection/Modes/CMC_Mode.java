package com.mr.or_atp.or_atp.models.automatic_train_protection.Modes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mr.or_atp.or_atp.models.automatic_train_protection.ATP_Panel;
import com.mr.or_atp.or_atp.models.automatic_train_protection.LightIndicatorStatus;
import com.mr.or_atp.or_atp.models.automatic_train_protection.SpeedIndicatorStatus;
import com.mr.or_atp.or_atp.models.sim_data.SimData;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorItem;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorSignal;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorSpeedLimit;

import lombok.Getter;

public class CMC_Mode implements ATP_ModeInterface {
    @Getter private int allowedSpeed;
    @Getter private int currentSpeed;
    @Getter private int objectiveSpeed;
    @Getter private int currentRouteSpeedLimit;
    private double distanceToObjective;
    private ATP_Panel panelStatus;

    private int timeOverLimit;

    @Override
    public void init() {
        allowedSpeed = 0;
        currentSpeed = 0;
        objectiveSpeed = 0;
        currentRouteSpeedLimit = 0;
        distanceToObjective = 0;
        timeOverLimit = 0;
        panelStatus = ATP_Panel.getInstance();
        panelStatus.setCmc_light(LightIndicatorStatus.ON);
    }

    @Override
    public ATP_OperationModes getMode() {
        return ATP_OperationModes.MANUAL_CONTROLLED_OPERATION;
    }

    @Override
    public void run(SimData simData) {
        currentSpeed = (int) Math.round(simData.getTrainDisplay().getSpeed());
        currentRouteSpeedLimit = (int) Math.round(simData.getTrackMonitor().getCurrentSpeedLimit());
        objectiveSpeed = calculateObjectiveSpeed(simData);
        allowedSpeed = calculateAllowedSpeed();
    }

    @Override
    public boolean isTractionAllowed() {
        return currentSpeed < allowedSpeed;
    }

    @Override
    public boolean isEmergencyBrakeForced() {
        //TODO: Penalty brake should be forced
        return timeOverLimit > 5 && currentSpeed > allowedSpeed;
    }

    private int calculateObjectiveSpeed(SimData simData) {
        ArrayList<MonitorSignal> nextSignals = simData.getTrackMonitor().getSignals();
        ArrayList<MonitorSpeedLimit> nextSpeedLimits = simData.getTrackMonitor().getSpeedLimits();
        int currentSpeedLimit = currentRouteSpeedLimit;

        //Get closest signals and speedLimits
        MonitorSignal closestSignal = nextSignals.stream().filter(s -> s.getDistance() < 500).min((a, b) -> Double.compare(a.getDistance(), b.getDistance())).orElse(null);
        MonitorSpeedLimit closestSpeedLimit = nextSpeedLimits.stream().filter(s -> s.getDistance() < 500).min((a, b) -> Double.compare(a.getDistance(), b.getDistance())).orElse(null);

        //Compare distances
        if(closestSignal != null && closestSpeedLimit != null) {
            if(closestSignal.getDistance() < closestSpeedLimit.getDistance()) {
                setDistanceToObjective(closestSignal.getDistance());
                int lower = Math.min(closestSignal.getSpeedLimit(), closestSpeedLimit.getSpeedLimit());
                return Math.min(lower, currentRouteSpeedLimit);
            } else {
                setDistanceToObjective(closestSpeedLimit.getDistance());
                int lower = Math.min(closestSignal.getSpeedLimit(), closestSpeedLimit.getSpeedLimit());
                return Math.min(lower, currentRouteSpeedLimit);
            }
        } else if(closestSignal != null) {
            setDistanceToObjective(closestSignal.getDistance());
            return Math.min(closestSignal.getSpeedLimit(), currentRouteSpeedLimit);
        } else if(closestSpeedLimit != null) {
                setDistanceToObjective(closestSpeedLimit.getDistance());
                return closestSpeedLimit.getSpeedLimit();
        } else {
            setDistanceToObjective(0);
            return currentRouteSpeedLimit;
        }



    }

    public int calculateAllowedSpeed() {
        // d * 3.6 / react_time = speed
        double brakingCurve = Math.min(distanceToObjective * 3.6 / 2 + objectiveSpeed, currentRouteSpeedLimit);
        return (int) Math.round(Math.max(brakingCurve, 10));
    }

    @Override
    public ATP_Panel getPanelIndications() {
        return panelStatus;
    }

    private void setDistanceToObjective(double distance) {
        distanceToObjective = distance;
    }
    
}
