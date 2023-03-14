package com.mr.or_atp.or_atp.models.track_monitor;

public enum SignalStatus {
    GREEN{
        public int maxSpeed() {
            return 120;
        }
    },
    GREENYELLOW{
        public int maxSpeed() {
            return 45;
        }
    },
    YELLOW{
        public int maxSpeed() {
            return 30;
        }
    },
    YELLOWRED{
        public int maxSpeed() {
            return 15;
        }
    },
    RED {
        public int maxSpeed() {
            return 0;
        }
    },
    REDWHITE{
        public int maxSpeed() {
            return 15;
        }
    },
    UNKNOWN{
        public int maxSpeed() {
            return 15;
        }
    };

    public int maxSpeed() {
        return 0;
    }
}
