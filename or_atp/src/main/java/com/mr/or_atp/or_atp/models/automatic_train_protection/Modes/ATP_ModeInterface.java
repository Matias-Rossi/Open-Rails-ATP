package com.mr.or_atp.or_atp.models.automatic_train_protection.Modes;

import com.mr.or_atp.or_atp.models.automatic_train_protection.ATP_Panel;
import com.mr.or_atp.or_atp.models.sim_data.SimData;

public interface ATP_ModeInterface {
    ATP_OperationModes getMode();
    void executeCalculations(SimData simData);
    boolean isTractionAllowed();
    boolean isEmergencyBrakeForced();
    Double objectiveSpeed();
    Double allowedSpeed();
    Double currentSpeed();
    ATP_Panel getPanelIndications();


}
