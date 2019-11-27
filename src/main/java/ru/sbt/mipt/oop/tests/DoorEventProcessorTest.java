package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.LightEventProcessor;

import java.util.Arrays;
import java.util.List;

class DoorEventProcessorTest {

    private final List<Integer> doorNums = Arrays.asList(1, 2, 3, 4);
    private SmartHome smartHome;
    private DoorEventProcessor doorEventProcessor;

    @BeforeEach
    void setUp() {
        Room bedroom = new Room(Arrays.asList(new Light("1", "bedroom", false),
                new Light("2", "bedroom", true),
                new Door("1", "bedroom", true),
                new Door("2", "bedroom", false)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("3", "hall", false),
                new Light("4", "hall", true),
                new Door("3", "hall", false),
                new Door("4", "hall", true)),
                "hall");

        smartHome = new SmartHome(Arrays.asList(bedroom, hall));
        doorEventProcessor = new DoorEventProcessor();
    }

    @Test
    void checkDoorOpenAfterDoorOpenEventProcess( ) {
        for (Integer doorNum : doorNums) {
            doorEventProcessor.processSensorEvent(
                    new SensorEvent(SensorEventType.DOOR_OPEN, Integer.toString(doorNum)),
                    smartHome);
            smartHome.execute(new TestUtils.CheckDoorState(true, Integer.toString(doorNum)));
        }
    }

    @Test
    void checkDoorClosedAfterDoorClosedEventProcess() {
        for (Integer doorNum : doorNums) {
            doorEventProcessor.processSensorEvent(
                    new SensorEvent(SensorEventType.DOOR_CLOSED, Integer.toString(doorNum)),
                    smartHome);
            smartHome.execute(new TestUtils.CheckDoorState(false, Integer.toString(doorNum)));
        }
    }
}