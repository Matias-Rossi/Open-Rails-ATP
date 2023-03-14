package com.mr.or_atp.or_atp.models.track_monitor;

import lombok.Getter;

public class MonitorSignal extends MonitorItem {
    private SignalStatus signalStatus;
    

    public MonitorSignal(SignalStatus signalStatus, Double distanceToItem) {
        super(distanceToItem);
        this.signalStatus = signalStatus;
    }

    @Override
    public int getSpeedLimit() {
        return getSignalSpeed();
    }

    public int getSignalSpeed(){
        switch (signalStatus) {
            case GREEN:
                return 120;
            case GREENYELLOW:
                return 45;
            case YELLOW:
                return 30;
            case YELLOWRED:
                return 15;
            case RED:
                return 0;
            case REDWHITE:
                return 15;
            case UNKNOWN:
                return 15;
            default:
                return 0;
        }
    }


    public SignalStatus getAspect() {
        return signalStatus;
    }
}
