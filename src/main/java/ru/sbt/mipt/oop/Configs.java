package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.HallClosingEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.LightEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.SensorEventProcessor;

import java.util.ArrayList;
import java.util.List;

public class Configs {
    public static List<SensorEventProcessor> getEventProcessors() {
        List<SensorEventProcessor> sensorEventProcessors = new ArrayList<>();
        sensorEventProcessors.add(new LightEventProcessor());
        sensorEventProcessors.add(new DoorEventProcessor());
        sensorEventProcessors.add(new HallClosingEventProcessor());
        return sensorEventProcessors;
    }
}
