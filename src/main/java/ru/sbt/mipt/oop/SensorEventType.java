package ru.sbt.mipt.oop;

public enum SensorEventType {
    LIGHT_ON {
        @Override
        public void setCode(String newCode) {

        }

        @Override
        public String getCode() {
            return null;
        }
    }, LIGHT_OFF {
        @Override
        public void setCode(String newCode) {

        }

        @Override
        public String getCode() {
            return null;
        }
    }, DOOR_OPEN {
        @Override
        public void setCode(String newCode) {

        }

        @Override
        public String getCode() {
            return null;
        }
    }, DOOR_CLOSED {
        @Override
        public void setCode(String newCode) {

        }

        @Override
        public String getCode() {
            return null;
        }
    },
    ALARM_ACTIVATE {
        @Override
        public void setCode(String newCode) {
            code = newCode;
        }

        @Override
        public String getCode() {
            return code;
        }

        String code;
    },
    ALARM_DEACTIVATE {
        @Override
        public void setCode(String newCode) {
            code = newCode;
        }

        @Override
        public String getCode() {
            return code;
        }

        String code;
    };

    public abstract String getCode();

    public abstract void setCode(String newCode);
}
