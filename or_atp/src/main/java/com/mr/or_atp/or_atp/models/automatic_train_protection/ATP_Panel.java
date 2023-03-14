package com.mr.or_atp.or_atp.models.automatic_train_protection;

import lombok.Getter;
import lombok.Setter;

public class ATP_Panel {
    @Getter @Setter LightIndicatorStatus cl_light;  //Green: Conducción Limitada - Limited Driving
    @Getter @Setter LightIndicatorStatus cmc_light; //Green: Conducción Manual Controlada - Controlled Manual Driving
    @Getter @Setter LightIndicatorStatus traction_light; //Yellow
    @Getter @Setter LightIndicatorStatus brake_light; //Yellow: On if BC > 0
    @Getter @Setter LightIndicatorStatus penalty_light; //Red: On if ATP is forcing the emergency brake

}
