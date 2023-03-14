package com.mr.or_atp.or_atp.models.track_monitor;

import lombok.Getter;

public class MonitorSpeedLimit extends MonitorItem {
    private int speedLimit;

    public MonitorSpeedLimit(Integer newSpeedLimit, Double distanceToItem) {
        super(distanceToItem);
        this.speedLimit = newSpeedLimit;
    }

    @Override
    public int getSpeedLimit() {
        return speedLimit;
    }
}
