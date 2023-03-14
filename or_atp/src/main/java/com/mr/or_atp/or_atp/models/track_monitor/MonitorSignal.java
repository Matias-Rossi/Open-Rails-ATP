package com.mr.or_atp.or_atp.models.track_monitor;

import lombok.Getter;

public class MonitorSignal extends MonitorItem {
    SignalStatus signalStatus;

    public MonitorSignal(SignalStatus signalStatus, Double distanceToItem) {
        super(distanceToItem);
        this.signalStatus = signalStatus;
    }

    public SignalStatus getAspect() {
        return signalStatus;
    }
}
