package com.mr.or_atp.or_atp.models.automatic_train_protection.Modes;

import com.mr.or_atp.or_atp.models.automatic_train_protection.ATP_Panel;
import com.mr.or_atp.or_atp.models.sim_data.SimData;

public interface ATP_ModeInterface {
    void init();
    ATP_OperationModes getMode();
    void run(SimData simData);
    boolean isTractionAllowed();
    boolean isEmergencyBrakeForced();
    double getObjectiveSpeed();
    double getAllowedSpeed();
    double getCurrentSpeed();
    ATP_Panel getPanelIndications();


}
