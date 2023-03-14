package com.mr.or_atp.or_atp.models.sim_data.json_models;

import java.util.Optional;

import com.mr.or_atp.or_atp.models.track_monitor.SignalStatus;

import lombok.Getter;

public class TrackMonitorRow {
    @Getter String FirstCol;
    @Getter String TrackColLeft;
    @Getter String TrackCol;
    @Getter TrackMonitorColItem TrackColItem;
    @Getter String TrackColRight;
    @Getter String LimitCol;
    String SignalCol;
    @Getter TrackMonitorColItem SignalColItem;
    String DistCol;

    public Optional<String> getSignalCol() {
        return this.hasSignal() ? Optional.of(SignalCol) : Optional.empty();
    }

    public boolean hasSignal() {
        return SignalColItem.Width > 0;
    }

    public boolean hasLimit() {
        return Character.isDigit(LimitCol.charAt(0));
    }

    public Optional<Integer> getLimit() {
        if (this.hasLimit()) {
            try {
                System.out.println("Parsing limit: >" + LimitCol + "<");
                String limitStr = "";
                for (char c : LimitCol.toCharArray()) {
                    if (Character.isDigit(c)) {
                        limitStr += c;
                    }
                }
                System.out.println("Got " + Integer.valueOf(limitStr));
                return Optional.of(Integer.valueOf(limitStr));
            } catch (Exception e) {}
        }
        return Optional.empty();
    }

    public boolean isSelfTrain() {
        return this.getTrackCol().contains("⧯");
    }

    public boolean hasDistance() {
        return DistCol != " ";
    }

    public Optional<Double> getDistance() {
        if (DistCol == " " || DistCol == "" || DistCol == null) {
            return Optional.empty();
        }

        boolean decimalFound = false;
        String whole = "";
        String decimal = "";
        //System.out.println("Parsing distance: >" + DistCol + "<");

        for (char c : DistCol.toCharArray()) {
            if (Character.isDigit(c)) {
                if (decimalFound) {
                    decimal += c;
                } else {
                    whole += c;
                }
            } else if (c == ',') {
                decimalFound = true;
            } else if (c == 'k') {
                return(Optional.of(Double.valueOf(whole + "." + decimal) * 1000));
            } else if (c == 'm') {
                return(Optional.of(Double.valueOf(whole)));
            }
        }
        return Optional.empty();
    }

    public SignalStatus getSignalAspect() {
        switch (SignalColItem.Y) {
            case 0: return SignalStatus.GREEN;
            case 16: return SignalStatus.GREENYELLOW;
            case 32: return SignalColItem.X == 0? SignalStatus.YELLOW : SignalStatus.YELLOWRED;
            case 48: return SignalColItem.X == 0? SignalStatus.YELLOWRED : SignalStatus.RED;
            case 64: return SignalStatus.REDWHITE;
            default: return SignalStatus.UNKNOWN;
        } 
    }


}
