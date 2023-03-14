package com.mr.or_atp.or_atp.models.sim_data;

import lombok.Getter;

public class SimData {
    @Getter private TrackMonitor trackMonitor;
    @Getter private TrainDisplay trainDisplay;
    @Getter private CabControls cabControls;
    
    public SimData(TrackMonitor trackMonitor, TrainDisplay trainDisplay, CabControls cabControls) {
        this.trackMonitor = trackMonitor;
        this.trainDisplay = trainDisplay;
        this.cabControls = cabControls;
    }
}
