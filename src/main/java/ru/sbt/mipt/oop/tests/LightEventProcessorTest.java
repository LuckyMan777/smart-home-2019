package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.eventprocessors.LightEventProcessor;

import java.util.Arrays;
import java.util.List;

class LightEventProcessorTest {
    private final List<Integer> lightNums = Arrays.asList(1, 2, 3, 4, 5, 6);
    private SmartHome smartHome;
    private LightEventProcessor lightEventProcessor;

    @BeforeEach
    void setUp() {
        Room bedroom = new Room(Arrays.asList(new Light("1", "bedroom", false),
                new Light("2", "bedroom", true),
                new Light("3", "bedroom", false),
                new Door("1", "bedroom", true)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("4", "hall", false),
                new Light("5", "hall", true),
                new Light("6", "hall", false),
                new Door("2", "hall", false)),
                "hall");

        smartHome = new SmartHome(Arrays.asList(bedroom, hall));
        lightEventProcessor = new LightEventProcessor();
    }

    @Test
    void checkLightOnAfterLightOnEventProcess() {
        for (Integer lightNum : lightNums) {
            lightEventProcessor.processSensorEvent(
                    new SensorEvent(SensorEventType.LIGHT_ON, Integer.toString(lightNum)),
                    smartHome);
            smartHome.execute(new TestUtils.CheckLightState(true, Integer.toString(lightNum)));
        }
    }

    @Test
    void checkLightOffAfterLightOffEventProcess() {
        for (Integer lightNum : lightNums) {
            lightEventProcessor.processSensorEvent(
                    new SensorEvent(SensorEventType.LIGHT_OFF, Integer.toString(lightNum)),
                    smartHome);
            smartHome.execute(new TestUtils.CheckLightState(false, Integer.toString(lightNum)));
        }
    }
}