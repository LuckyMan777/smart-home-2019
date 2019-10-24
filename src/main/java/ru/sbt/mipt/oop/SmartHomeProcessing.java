package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessors.SensorEventProcessor;
import ru.sbt.mipt.oop.eventproviders.SensorEventProvider;

import java.util.Collection;

public class SmartHomeProcessing {
    private SmartHome smartHome;
    private SensorEventProvider sensorEventProvider;
    private Collection<SensorEventProcessor> sensorEventProcessors;

    public SmartHomeProcessing(SmartHome smartHome, SensorEventProvider sensorEventProvider) {
        this.smartHome = smartHome;
        this.sensorEventProvider = sensorEventProvider;
        sensorEventProcessors = Configs.getEventProcessors();
    }

    public void smartHomeProcess() {
        SensorEvent sensorEvent = sensorEventProvider.provideNextSensorEvent();

        while (sensorEvent != null) {
            processSensorEvent(sensorEvent);
            sensorEvent = sensorEventProvider.provideNextSensorEvent();
        }
    }

    public void processSensorEvent(SensorEvent sensorEvent) {
        System.out.println("Got event: " + sensorEvent);
        for (SensorEventProcessor sensorEventProcessor : sensorEventProcessors) {
            sensorEventProcessor.processSensorEvent(sensorEvent, smartHome);
        }
    }
}
