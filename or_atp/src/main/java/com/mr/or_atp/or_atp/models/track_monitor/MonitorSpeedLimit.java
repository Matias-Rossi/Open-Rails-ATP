package com.mr.or_atp.or_atp.models.track_monitor;

import lombok.Getter;

public class MonitorSpeedLimit extends MonitorItem {
    @Getter Integer speedLimit;

    public MonitorSpeedLimit(Integer newSpeedLimit, Double distanceToItem) {
        super(distanceToItem);
        this.speedLimit = newSpeedLimit;
    }
}
