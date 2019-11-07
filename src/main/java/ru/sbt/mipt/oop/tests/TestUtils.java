package ru.sbt.mipt.oop.tests;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {
    public static void checkDoorOpenClosed(SmartHome smartHome, String id, boolean isOpen) {
        smartHome.execute(object -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(id))
                    assertEquals(door.isOpen(), isOpen);
            }
        });
    }

    public static void checkLightOnOff(SmartHome smartHome, String id, boolean isOn) {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(id))
                    assertEquals(light.isOn(), isOn);
            }
        });
    }

    public static void checkDoorOpenClosedInRoom(SmartHome smartHome, String id, boolean isOpen, String roomName) {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals(roomName))
                {

                }
            }
        });
    }

    public static void checkLightOnOffInRoom(SmartHome smartHome, String id, boolean isOn, String roomName) {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(id))
                    assertEquals(light.isOn(), isOn);
            }
        });
    }
}
