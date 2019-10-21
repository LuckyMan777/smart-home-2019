package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;

import java.util.Arrays;
import java.util.List;

class DoorEventProcessorTest {

    private SmartHome smartHome;
    private final List<Integer> doorNums = Arrays.asList(1, 2, 3, 4);

    @BeforeEach
    void setUp() {
        Room bedroom = new Room(Arrays.asList(new Light("1", false), new Light("2", true),
                new Door("1", true), new Door("2", false)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("3", false), new Light("4", true),
                new Door("3", false), new Door("4", true)),
                "hall");

        smartHome = new SmartHome(Arrays.asList(bedroom, hall));
    }

    @Test
    void checkDoorOpenAfterDoorOpenEventProcess() {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(smartHome, null);

        for (Integer doorNum : doorNums) {
            smartHomeProcessing.processSensorEvent(
                    new SensorEvent(SensorEventType.DOOR_OPEN, Integer.toString(doorNum)));
            TestUtils.checkDoorOnOff(smartHome, Integer.toString(doorNum), true);
        }
    }

    @Test
    void checkDoorClosedAfterDoorClosedEventProcess() {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(smartHome, null);

        for (Integer doorNum : doorNums) {
            smartHomeProcessing.processSensorEvent(
                    new SensorEvent(SensorEventType.DOOR_CLOSED, Integer.toString(doorNum)));
            TestUtils.checkDoorOnOff(smartHome, Integer.toString(doorNum), false);
        }
    }
}