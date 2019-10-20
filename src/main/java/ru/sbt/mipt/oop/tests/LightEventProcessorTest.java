package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.smartDevices.Door;
import ru.sbt.mipt.oop.smartDevices.Light;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class LightEventProcessorTest {
    private SmartHome smartHome;
    private final List<Integer> lightNums = Arrays.asList(1, 2, 3, 4, 5, 6);

    @BeforeEach
    void setUp() {
        Room bedroom = new Room(Arrays.asList(new Light("1", false), new Light("2", true),
                new Light("3", false), new Door("1", true)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("4", false), new Light("5", true),
                new Light("6", false), new Door("2", false)),
                "hall");

        smartHome = new SmartHome(Arrays.asList(bedroom, hall));
    }

    @Test
    void checkLightOnAfterLightOnEventProcess() {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(smartHome, null);

        for (Integer lightNum : lightNums) {
            smartHomeProcessing.processSensorEvent(
                    new SensorEvent(SensorEventType.LIGHT_ON, Integer.toString(lightNum)));
            TestUtils.checkLightOnOff(smartHome, Integer.toString(lightNum), true);
        }
    }

    @Test
    void checkLightOffAfterLightOffEventProcess() {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(smartHome, null);

        for (Integer lightNum : lightNums) {
            smartHomeProcessing.processSensorEvent(
                    new SensorEvent(SensorEventType.LIGHT_OFF, Integer.toString(lightNum)));
            TestUtils.checkLightOnOff(smartHome, Integer.toString(lightNum), false);
        }
    }
}