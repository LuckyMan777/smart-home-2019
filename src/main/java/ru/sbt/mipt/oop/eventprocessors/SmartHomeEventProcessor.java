package ru.sbt.mipt.oop.eventprocessors;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Collection;

public class SmartHomeEventProcessor implements EventProcessor {

    private final Collection<EventProcessor> eventProcessors;

    public SmartHomeEventProcessor(Collection<EventProcessor> processors) {
        this.eventProcessors = processors;
    }

    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        System.out.println("Got event: " + sensorEvent);
        for (EventProcessor sensorEventProcessor : eventProcessors) {
            sensorEventProcessor.processSensorEvent(sensorEvent, smartHome);
        }
    }

    @Override
    public boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        return true;
    }
}
