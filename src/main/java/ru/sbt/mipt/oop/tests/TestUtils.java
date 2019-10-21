package ru.sbt.mipt.oop.tests;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {
    protected static void checkDoorOnOff(SmartHome smartHome, String id, boolean isOpen) {
        smartHome.execute(new Action() {
            @Override
            public void execute(Object object) {
                if (object instanceof Door){
                    Door door = (Door) object;
                    if (door.getId().equals(id))
                        assertEquals(door.isOpen(), isOpen);
                }
            }
        });
    }

    protected static void checkLightOnOff(SmartHome smartHome, String id, boolean isOn) {
        smartHome.execute(new Action() {
            @Override
            public void execute(Object object) {
                if (object instanceof Light){
                    Light light = (Light) object;
                    if (light.getId().equals(id))
                        assertEquals(light.isOn(), isOn);
                }
            }
        });
    }
}
