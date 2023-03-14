package com.mr.or_atp.or_atp.models.automatic_train_protection.panel_json;

import com.mr.or_atp.or_atp.models.automatic_train_protection.LightIndicatorStatus;
import com.mr.or_atp.or_atp.models.automatic_train_protection.SpeedIndicatorStatus;

import lombok.Getter;

public class SpeedJSON {
    @Getter
    private String name;

    @Getter
    private double speed;
    
    @Getter 
    private String status;


    public SpeedJSON(String name, SpeedIndicatorStatus speedIndicatorStatus) {
        this.name = name;
        this.speed = speedIndicatorStatus.getSpeed();
        switch(speedIndicatorStatus.getLight()){
        case ON:
            this.status = "ON";
            break;
        case OFF:
            this.status = "OFF";
            break;
        case BLINKING:
            this.status = "BLINKING";
            break;
        }
    }

    public static String createObject(String name, SpeedIndicatorStatus speedIndicatorStatus) {
        String str = "{";
        str += "\"name\": \"" + name + "\",";
        str += "\"speed\": \"" + speedIndicatorStatus.getSpeed() + "\",";
        str += "\"status\": \"" + LightIndicatorJSON.statusToString(speedIndicatorStatus.getLight()) + "\"";
        str += "}";
        return str;
    }

}
