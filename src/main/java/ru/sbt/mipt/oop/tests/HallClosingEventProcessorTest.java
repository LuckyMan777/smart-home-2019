package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.eventprocessors.HallClosingEventProcessor;

import java.util.Arrays;
import java.util.List;

class HallClosingEventProcessorTest {
    private final List<Integer> lightNums = Arrays.asList(1, 2, 3, 4, 5, 6);
    private final int hallDoorNum = 2;
    private SmartHome smartHome;
    private HallClosingEventProcessor hallClosingEventProcessor;

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
        hallClosingEventProcessor = new HallClosingEventProcessor();
    }

    @Test
    void checkLightsOffAfterHallClosingEventProcess() {
        hallClosingEventProcessor.processSensorEvent(
                new SensorEvent(SensorEventType.DOOR_CLOSED, Integer.toString(hallDoorNum)),
                smartHome);

        smartHome.execute(new TestUtils.CheckAllLightStates(false));
    }

}