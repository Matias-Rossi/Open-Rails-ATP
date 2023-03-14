package com.mr.or_atp.or_atp.models.automatic_train_protection;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mr.or_atp.or_atp.models.automatic_train_protection.Modes.ATP_ModeInterface;
import com.mr.or_atp.or_atp.models.automatic_train_protection.Modes.CMC_Mode;
import com.mr.or_atp.or_atp.models.sim_data.SimData;
import com.mr.or_atp.or_atp.realtime_data_fetcher.DataFetcher;

import lombok.Getter;


@Component
public class ATP_core {
    static private ATP_ModeInterface selectedMode = new CMC_Mode();
    DataFetcher dataFetcher = DataFetcher.getInstance();

    @Scheduled(fixedDelay = 1000)
    public void run() {
        SimData simData = dataFetcher.fetchData();  
        selectedMode.run(simData);

        //System.out.println("Act.: " + selectedMode.getCurrentSpeed() + " // Obj.: " + selectedMode.getObjectiveSpeed() + " // All.: " + selectedMode.getAllowedSpeed() + " // Traction: " + selectedMode.isTractionAllowed() + " // EBrake: " + selectedMode.isEmergencyBrakeForced() + " // Mode: " + selectedMode.getMode());
    }

    static public ATP_Panel getPanel() {
        return selectedMode.getPanelIndications();
    }


}
