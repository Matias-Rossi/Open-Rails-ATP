package com.mr.or_atp.or_atp.models.sim_data;

import java.util.ArrayList;
import java.util.Arrays;

import com.mr.or_atp.or_atp.models.sim_data.json_models.CabControl;

public class CabControls {
    ArrayList<CabControl> cabControls;

    public CabControls(CabControl[] cabControls) {
        this.cabControls = new ArrayList<>(Arrays.asList(cabControls));
    }
}
