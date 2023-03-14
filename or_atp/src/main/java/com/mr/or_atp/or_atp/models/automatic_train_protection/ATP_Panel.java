package com.mr.or_atp.or_atp.models.automatic_train_protection;

import lombok.Getter;
import lombok.Setter;

public class ATP_Panel {
    @Getter @Setter LightIndicatorStatus cl_light;  //Green: Conducción Limitada - Limited Driving
    @Getter @Setter LightIndicatorStatus cmc_light; //Green: Conducción Manual Controlada - Controlled Manual Driving
    @Getter @Setter LightIndicatorStatus traction_light; //Yellow
    @Getter @Setter LightIndicatorStatus brake_light; //Yellow: On if BC > 0
    @Getter @Setter LightIndicatorStatus penalty_light; //Red: On if ATP is forcing the emergency brake
    @Getter @Setter SpeedIndicatorStatus current_speed;
    @Getter @Setter SpeedIndicatorStatus allowed_speed;
    @Getter @Setter SpeedIndicatorStatus objective_speed;

    private static ATP_Panel instance = null;

    public static ATP_Panel getInstance() {
        if (instance == null) {
            instance = new ATP_Panel();
        }
        return instance;
    }

    public void reset() {
        cl_light = LightIndicatorStatus.OFF;
        cmc_light = LightIndicatorStatus.OFF;
        traction_light = LightIndicatorStatus.OFF;
        brake_light = LightIndicatorStatus.OFF;
        penalty_light = LightIndicatorStatus.OFF;
        current_speed = new SpeedIndicatorStatus();
        allowed_speed = new SpeedIndicatorStatus();
        objective_speed = new SpeedIndicatorStatus();
    }

    private ATP_Panel() {
        reset();
    }

}

