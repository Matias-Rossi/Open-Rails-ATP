package com.mr.or_atp.or_atp.models.sim_data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mr.or_atp.or_atp.models.sim_data.json_models.TrackMonitorRow;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorSignal;
import com.mr.or_atp.or_atp.models.track_monitor.MonitorSpeedLimit;

public class TrackMonitor {
    ArrayList<TrackMonitorRow> rows;

    public TrackMonitor(TrackMonitorRow[] rows) {
        this.rows = new ArrayList<TrackMonitorRow>(Arrays.asList(rows));
    }

    public ArrayList<MonitorSignal> getSignals() {

        ArrayList<MonitorSignal> monitorSignals = new ArrayList<MonitorSignal>();

        boolean trainReached = false;
        double lastDistance = 9999;

        for (int i=0; i < this.rows.size() && !trainReached; i++) {
            TrackMonitorRow row = this.rows.get(i);
            if (row.hasSignal()) {
                boolean hasDistance = row.hasDistance();
                double signalDistance;
                if (hasDistance) {
                    try {
                        signalDistance = row.getDistance().get();
                        lastDistance = signalDistance;
                    } catch (Exception e) {
                        signalDistance = lastDistance;    //TODO Limpiar función
                    }
                } else {
                    signalDistance = lastDistance;
                }
                MonitorSignal newSignal = new MonitorSignal(row.getSignalAspect(), signalDistance);
                monitorSignals.add(newSignal);
            }
            if (row.isSelfTrain()) {
                trainReached = true;
            }
        }

        return monitorSignals;
    }

    public ArrayList<MonitorSpeedLimit> getSpeedLimits() {
        ArrayList<MonitorSpeedLimit> monitorSpeedLimits = new ArrayList<MonitorSpeedLimit>();

        boolean trainReached = false;
        double lastDistance = 9999;

        for (int i=0; i < this.rows.size() && !trainReached; i++) {
            TrackMonitorRow row = this.rows.get(i);
            if (row.hasLimit()) {
                boolean hasDistance = row.hasDistance();
                double signalDistance;
                if (hasDistance) {
                    try {
                        signalDistance = row.getDistance().get();
                        lastDistance = signalDistance;
                    } catch (Exception e) {
                        signalDistance = lastDistance;    //TODO Limpiar función    
                    }
                } else {
                    signalDistance = lastDistance;
                }
                try {
                    MonitorSpeedLimit newSpeedLimit = new MonitorSpeedLimit(row.getLimit().get(), signalDistance);
                    monitorSpeedLimits.add(newSpeedLimit);
                } catch (Exception e) {}
            }
            if (row.isSelfTrain()) {
                trainReached = true;
            }
        }

        return monitorSpeedLimits;
    }

    public double getCurrentSpeedLimit() {
        return Double.valueOf(this.rows.get(2).getTrackCol().split(" ")[0]);
    }
    
}
