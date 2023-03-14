package com.mr.or_atp.or_atp.models.automatic_train_protection.Modes;

public enum ATP_OperationModes {
    ISOLATED_LIMITED,
        //Only in case of on board equipment failure. Not to be used for normal operations.
        //Does not operate brakes but will limit traction over 25 km/h. Accesible through switch outside of cab.
    ISOLATED_TOTAL,
        //Similar to ISOLATED_LIMITED, but will not limit traction. Accesible through switch outside of cab.
        //To access it a special key is needed.
    LIMITED_OPERATION, //Ignores all signals, but speed is limited to 10km/h
    MANUAL_CONTROLLED_OPERATION //Normal driving mode
    //Last 2 are toggled using a switch inside the cab.
    
}
