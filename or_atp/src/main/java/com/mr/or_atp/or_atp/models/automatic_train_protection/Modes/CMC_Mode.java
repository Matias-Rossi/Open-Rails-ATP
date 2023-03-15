package com.mr.or_atp.or_atp.models.automatic_train_protection.Modes;

import java.util.ArrayList;
import com.mr.or_atp.or_atp.models.automatic_train_protection.ATP_Panel;
import com.mr.or_atp.or_atp.models.automatic_train_protection.LightIndicatorStatus;
import com.mr.or_atp.or_atp.models.sim_data.SimData;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorSignal;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorSpeedLimit;

import lombok.Getter;

public class CMC_Mode implements ATP_ModeInterface {
    @Getter private double allowedSpeed;
    @Getter private double currentSpeed;
    @Getter private double objectiveSpeed;
    @Getter private int currentRouteSpeedLimit;
    private double distanceToObjective;
    @Getter private ATP_Panel panelStatus;
    private double lastSpeed;
    private double acceleration;
    private boolean isBrakeApplied;

    private int timeOverLimit; //TODO: Pending implementation

    private boolean initExecuted = false;

    @Override
    public void init() {
        allowedSpeed = 0;
        currentSpeed = 0;
        objectiveSpeed = 0;
        currentRouteSpeedLimit = 0;
        distanceToObjective = 0;
        timeOverLimit = 0;
        lastSpeed = 0;
        panelStatus = ATP_Panel.getInstance();
        panelStatus.reset();
        panelStatus.setCmc_light(LightIndicatorStatus.ON);
    }

    @Override
    public ATP_OperationModes getMode() {
        return ATP_OperationModes.MANUAL_CONTROLLED_OPERATION;
    }

    @Override
    public void run(SimData simData) {
        if(!initExecuted) {
            init();
            initExecuted = true;
        }

        lastSpeed = currentSpeed;
        acceleration = (currentSpeed/3.6 - lastSpeed/3.6) / 0.5;
        currentSpeed = simData.getTrainDisplay().getSpeed();
        currentRouteSpeedLimit = (int) Math.round(simData.getTrackMonitor().getCurrentSpeedLimit());
        objectiveSpeed = calculateObjectiveSpeed(simData);
        allowedSpeed = calculateAllowedSpeed();
        System.out.println("Allowed speed: " + allowedSpeed);
        isBrakeApplied = simData.getTrainDisplay().isBrakeApplied();

        //Update panel
        panelStatus.setSpeeds(currentSpeed, objectiveSpeed, allowedSpeed);
        panelStatus.setTraction_light(isTractionAllowed()? LightIndicatorStatus.ON : LightIndicatorStatus.OFF);
        panelStatus.setBrake_light(isBrakeApplied? LightIndicatorStatus.ON : LightIndicatorStatus.OFF);
        panelStatus.setPenalty_light(isEmergencyBrakeForced()? LightIndicatorStatus.ON : LightIndicatorStatus.OFF);
    }

    @Override
    public boolean isTractionAllowed() {
        return currentSpeed < allowedSpeed && !isBrakeApplied;
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
                if (closestSignal.getSpeedLimit() < closestSpeedLimit.getSpeedLimit()) {
                    setDistanceToObjective(closestSignal.getDistance());
                    return Math.min(closestSignal.getSpeedLimit(), currentRouteSpeedLimit);
                } else {
                    setDistanceToObjective(closestSpeedLimit.getDistance());
                    return Math.min(closestSpeedLimit.getSpeedLimit(), currentRouteSpeedLimit);
                }
            } else {
                if (closestSignal.getSpeedLimit() < closestSpeedLimit.getSpeedLimit()) {
                    setDistanceToObjective(closestSignal.getDistance());
                    return Math.min(closestSignal.getSpeedLimit(), currentRouteSpeedLimit);
                } else {
                    setDistanceToObjective(closestSpeedLimit.getDistance());
                    return Math.min(closestSpeedLimit.getSpeedLimit(), currentRouteSpeedLimit);
                }
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
        //double brakingCurve = Math.min(distanceToObjective * 0.25 / 2 + objectiveSpeed, currentRouteSpeedLimit);
        /*
         * d = (v^2 - v_r^2) / (2 * a)
         * Where:
         * d is the distance required to come to a complete stop from the current speed (in meters).
         * v is the current speed of the train (in meters per second).
         * v_r is the speed restriction (in meters per second).
         * a is the current deceleration of the train (in meters per second squared).
         */

        double brakingCurve = Math.sqrt(0.75 * Math.max(distanceToObjective - 50, 0) * 2 + Math.pow(objectiveSpeed/3.6, 2)) * 3.6;
        double minSpeed = Math.min(brakingCurve, currentRouteSpeedLimit);
        return (int) Math.round(Math.max(minSpeed, 5));
    }

    @Override
    public ATP_Panel getPanelIndications() {
        return panelStatus;
    }

    private void setDistanceToObjective(double distance) {
        distanceToObjective = distance;
    }
    
}
