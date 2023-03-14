package com.mr.or_atp.or_atp.models.sim_data;

import java.util.ArrayList;
import java.util.Arrays;

import com.mr.or_atp.or_atp.models.sim_data.json_models.TrainDisplayRow;

public class TrainDisplay {
    ArrayList<TrainDisplayRow> rows;

    public TrainDisplay(TrainDisplayRow[] rows) {
        this.rows = new ArrayList<TrainDisplayRow>(Arrays.asList(rows));
    }

    public Double getSpeed() {
        try {
            String str = rows.get(1).getLastCol();
            String[] num = str.split(" ")[0].split(",");
            Double velocity = Double.parseDouble(num[0]) + Double.parseDouble(num[1]) / 10;
            return Math.abs(velocity);
        } catch (Exception e) {
            return -1.0;
        }
    }

    private TrainDisplayRow matchFirstCol(String str) {
        for (TrainDisplayRow row : rows) {
            if (row.getFirstCol().equals(str)) {
                return row;
            }
        }
        return null;
    }

    public Double getBrakeCylinderPressure() {
        for (TrainDisplayRow row : rows) {
            String lastColStr = row.getLastCol();
            if (lastColStr.contains("BC")) {
                try {
                    String[] num = lastColStr.split(" ")[1].split(",");
                    return Double.parseDouble(num[0]) + Double.parseDouble(num[1]) / 10;
                } catch (Exception e) {
                    return -1.0;
                }
            }
        }
        return -1.0;
    }

    public Boolean isBrakeApplied() {
        return getBrakeCylinderPressure() > 0;
    }

    public Boolean isPowered() {
        try {
            return matchFirstCol("Batterieschalter").getLastCol().equals("An");
        } catch (Exception e) {
            return false;
        }
    }
    
}
