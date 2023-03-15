package com.mr.or_atp.or_atp.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mr.or_atp.or_atp.models.automatic_train_protection.ATP_Panel;
import com.mr.or_atp.or_atp.models.automatic_train_protection.ATP_core;
import com.mr.or_atp.or_atp.models.automatic_train_protection.panel_json.LightIndicatorJSON;
import com.mr.or_atp.or_atp.models.automatic_train_protection.panel_json.SpeedJSON;

@RestController
public class ATPController {

    @CrossOrigin
    @GetMapping("/atp")
    public String getATPPanel() {
        ATP_Panel panel = ATP_core.getPanel();

        String ret = "[";

        //Lights
        ret += LightIndicatorJSON.createObject("CL", panel.getCl_light())       + ",";
        ret += LightIndicatorJSON.createObject("CMC", panel.getCmc_light())     + ",";
        ret += LightIndicatorJSON.createObject("TR", panel.getTraction_light()) + ",";
        ret += LightIndicatorJSON.createObject("BR", panel.getBrake_light())    + ",";
        ret += LightIndicatorJSON.createObject("PEN", panel.getPenalty_light()) + ",";
        //Speeds
        ret += SpeedJSON.createObject("CUR", panel.getCurrent_speed()) + ",";
        ret += SpeedJSON.createObject("OBJ", panel.getObjective_speed()) + ",";
        ret += SpeedJSON.createObject("ALL", panel.getAllowed_speed()) + "]";

        return ret;
    }
    
}
