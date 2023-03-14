package com.mr.or_atp.or_atp.models.sim_data;

public class SimData {
    TrackMonitor trackMonitor;
    TrainDisplay trainDisplay;
    CabControls cabControls;
    
    public SimData(TrackMonitor trackMonitor, TrainDisplay trainDisplay, CabControls cabControls) {
        this.trackMonitor = trackMonitor;
        this.trainDisplay = trainDisplay;
        this.cabControls = cabControls;
    }
}
