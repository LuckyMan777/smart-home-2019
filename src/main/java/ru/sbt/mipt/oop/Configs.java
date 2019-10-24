package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessors.*;

import java.util.ArrayList;
import java.util.List;

public class Configs {
    public static List<EventProcessor> getEventProcessors() {
        List<EventProcessor> sensorEventProcessors = new ArrayList<>();
        sensorEventProcessors.add(new SignalizationDecorator(new LightEventProcessor()));
        sensorEventProcessors.add(new SignalizationDecorator(new DoorEventProcessor()));
        sensorEventProcessors.add(new SignalizationDecorator(new HallClosingEventProcessor()));
        sensorEventProcessors.add(new SignalizationDecorator(new SignalizationActivatedEventProcessor()));
        sensorEventProcessors.add(new SignalizationDecorator(new SignalizationDeactivatedEventProcessor()));
        return sensorEventProcessors;
    }
}
