package com.mr.or_atp.or_atp.models.automatic_train_protection.panel_json;

import com.mr.or_atp.or_atp.models.automatic_train_protection.LightIndicatorStatus;

import lombok.Getter;

public class LightIndicatorJSON {
    @Getter
    private String name;

    @Getter 
    private String status;

    public LightIndicatorJSON(String name, LightIndicatorStatus status) {
        this.name = name;
        this.status = statusToString(status);
    }

    public static String createObject(String name, LightIndicatorStatus status) {
        String str = "{";
        str += "\"name\": \"" + name + "\",";
        str += "\"status\": \"" + statusToString(status) + "\"";
        str += "}";
        return str;
    }

    //TODO: Move this function from here to LightIndicatorStatus class
    public static String statusToString(LightIndicatorStatus status) {
        switch(status){
            case ON:
                return "ON";

            case OFF:
                return "OFF";
                
            case BLINKING:
                return "BLINKING";
            
        }
        return "OFF";
    }
}
