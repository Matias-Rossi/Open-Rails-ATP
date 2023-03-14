package com.mr.or_atp.or_atp.models.track_monitor;

public abstract class MonitorItem {
    Double distanceToItem;

    public MonitorItem(Double distanceToItem) {
        this.distanceToItem = distanceToItem; //meters
    }

    public int getSpeedLimit() {
        return 0;
    }

    public Double getDistance() {
        return distanceToItem;
    }
}
