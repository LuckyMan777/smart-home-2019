package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;

import java.util.Arrays;
import java.util.List;

class HallClosingEventProcessorTest {
    private SmartHome smartHome;
    private final List<Integer> lightNums = Arrays.asList(1, 2, 3, 4, 5, 6);
    private final int hallDoorNum = 2;

    @BeforeEach
    void setUp() {
        //Every light is ON
        Room bedroom = new Room(Arrays.asList(new Light("1", "bedroom", true),
                new Light("2", "bedroom", true),
                new Light("3", "bedroom", true),
                new Door("1", "bedroom", true)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("4", "hall", true),
                new Light("5", "hall", true),
                new Light("6", "hall", true),
                new Door("2", "hall", true)),
                "hall");

        smartHome = new SmartHome(Arrays.asList(bedroom, hall));
    }

    @Test
    void checkLightsOffAfterHallClosingEventProcess() {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(smartHome, null);
        smartHomeProcessing.processSensorEvent(
                new SensorEvent(SensorEventType.DOOR_CLOSED, Integer.toString(hallDoorNum)));

        for (Integer lightNotInHallNum : lightNums) {
            TestUtils.checkLightOnOff(smartHome, Integer.toString(lightNotInHallNum), false);
        }
    }

}