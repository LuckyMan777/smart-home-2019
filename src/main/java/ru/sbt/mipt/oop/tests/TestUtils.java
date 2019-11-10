package ru.sbt.mipt.oop.tests;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.Signalization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {

    static class CheckAllLightStates implements Action {
        private boolean isOn;

        public CheckAllLightStates(boolean isOn) {
            this.isOn = isOn;
        }

        @Override
        public void execute(Object object) {
            if (object instanceof Light) {
                Light light = (Light) object;
                assertEquals(light.isOn(), isOn);
            }
        }
    }

    static class CheckAllLightStatesInRoom implements Action {
        private String roomName;
        private boolean isOn;

        public CheckAllLightStatesInRoom(boolean isOn, String room) {
            this.roomName = room;
            this.isOn = isOn;
        }

        @Override
        public void execute(Object object) {
            if (object instanceof Room){
                Room room = (Room) object;
                if (room.getName().equals(roomName))
                {
                    room.execute(new CheckAllLightStates(isOn));
                }
            }
        }
    }

    static class CheckDoorStateInRoom implements Action {
        private boolean isOpen;
        private String roomName;

        public CheckDoorStateInRoom(boolean isOpen, String roomName) {
            this.isOpen = isOpen;
            this.roomName = roomName;
        }

        @Override
        public void execute(Object object) {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getRoomName().equals(roomName)) {
                    assertEquals(door.isOpen(), isOpen);
                }
            }
        }
    }

    static class CheckDoorState implements Action {
        private boolean isOpen;
        private String doorId;

        public CheckDoorState(boolean isOpen, String doorId) {
            this.isOpen = isOpen;
            this.doorId = doorId;
        }

        @Override
        public void execute(Object object) {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(doorId)) {
                    assertEquals(door.isOpen(), isOpen);
                }
            }
        }
    }

    static class CheckLightState implements Action {
        private boolean isOn;
        private String lightId;

        public CheckLightState(boolean isOpen, String lightId) {
            this.isOn = isOpen;
            this.lightId = lightId;
        }

        @Override
        public void execute(Object object) {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(lightId)) {
                    assertEquals(light.isOn(), isOn);
                }
            }
        }
    }

    public static Signalization getSignalization(SmartHome smartHome){
        final Signalization[] signalization = new Signalization[1];
        smartHome.execute(object -> {
            if (object instanceof Signalization) {
                signalization[0] = (Signalization) object;
            }
        });
        return signalization[0];
    }
}
