package com.mr.or_atp.or_atp.models.automatic_train_protection.Modes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mr.or_atp.or_atp.models.automatic_train_protection.ATP_Panel;
import com.mr.or_atp.or_atp.models.sim_data.SimData;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorItem;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorSignal;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorSpeedLimit;

import lombok.Getter;

public class CMC_Mode implements ATP_ModeInterface {
    @Getter private double allowedSpeed;
    @Getter private double currentSpeed;
    @Getter private double objectiveSpeed;
    @Getter private double currentRouteSpeedLimit;

    private int timeOverLimit;

    @Override
    public ATP_OperationModes getMode() {
        return ATP_OperationModes.MANUAL_CONTROLLED_OPERATION;
    }

    @Override
    public void executeCalculations(SimData simData) {
        objectiveSpeed = calculateObjectiveSpeed(simData);
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

    private Double calculateObjectiveSpeed(SimData simData) {
        ArrayList<MonitorSignal> nextSignals = simData.getTrackMonitor().getSignals();
        ArrayList<MonitorSpeedLimit> nextSpeedLimits = simData.getTrackMonitor().getSpeedLimits();
        double currentSpeedLimit = simData.getTrackMonitor().getCurrentSpeedLimit();

        //Get signal with lowest allowed speed in the next 500m
        Optional<MonitorSignal> maybeMinSignal =  nextSignals.stream()
            .filter(s -> s.getDistance() < 500)
            .min((s1, s2) -> s1.getAspect().maxSpeed() - s2.getAspect().maxSpeed());

        //Get slowest speed limit in the next 500m
        Optional<MonitorSpeedLimit> maybeMinSpeedLimit = nextSpeedLimits.stream()
            .filter(s -> s.getDistance() < 500)
            .min((s1, s2) -> s1.getSpeedLimit() - s2.getSpeedLimit());

        int minSignalSpeed = maybeMinSignal.isPresent() ? maybeMinSignal.get().getAspect().maxSpeed() : Integer.MAX_VALUE;
        int minSpeedLimit = maybeMinSpeedLimit.isPresent() ? maybeMinSpeedLimit.get().getSpeedLimit() : Integer.MAX_VALUE;
      
    }

    public double calculateAllowedSpeed() {
        double brakingCurve = 
    }

    @Override
    public Double currentSpeed() {
        return currentSpeed;
    }

    @Override
    public ATP_Panel getPanelIndications() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPanelIndications'");
    }
    
}
