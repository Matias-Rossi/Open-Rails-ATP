package com.mr.or_atp.or_atp.models.automatic_train_protection;

import lombok.Setter;

public class SpeedIndicatorStatus {
    @Setter double speed;
    @Setter LightIndicatorStatus light;

    public SpeedIndicatorStatus() {
        this.speed = 0.0;
        this.light = LightIndicatorStatus.OFF;
    }
}
