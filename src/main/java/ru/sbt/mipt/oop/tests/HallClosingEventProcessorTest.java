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
    private final List<Integer> lightInHallNums = Arrays.asList(4, 5, 6);
    private final List<Integer> lightNotInHallNums = Arrays.asList(1, 2, 3);
    private final int hallDoorNum = 2;

    @BeforeEach
    void setUp() {
        //Every light is ON
        Room bedroom = new Room(Arrays.asList(new Light("1", true), new Light("2", true),
                new Light("3", true), new Door("1", true)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("4", true), new Light("5", true),
                new Light("6", true), new Door("2", true)),
                "hall");

        smartHome = new SmartHome(Arrays.asList(bedroom, hall));
    }

    @Test
    void checkLightsOnAfterHallClosingEventProcess() {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(smartHome, null);
        smartHomeProcessing.processSensorEvent(
                new SensorEvent(SensorEventType.DOOR_CLOSED, Integer.toString(hallDoorNum)));

        for (Integer lightNotInHallNum : lightNotInHallNums) {
            TestUtils.checkLightOnOff(smartHome, Integer.toString(lightNotInHallNum), true);
        }
    }

    @Test
    void checkLightsOffAfterHallClosingEventProcess() {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(smartHome, null);
        smartHomeProcessing.processSensorEvent(
                new SensorEvent(SensorEventType.DOOR_CLOSED, Integer.toString(hallDoorNum)));

        for (Integer lightNotInHallNum : lightInHallNums) {
            TestUtils.checkLightOnOff(smartHome, Integer.toString(lightNotInHallNum), false);
        }
    }

}