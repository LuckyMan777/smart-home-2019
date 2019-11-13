package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventproviders.SensorEventProvider;

import java.util.Collection;

public class SmartHomeProcessing {
    private SmartHome smartHome;
    private SensorEventProvider sensorEventProvider;
    private Collection<EventProcessor> eventProcessors;

    public SmartHomeProcessing(SmartHome smartHome, SensorEventProvider sensorEventProvider) {
        this.smartHome = smartHome;
        this.sensorEventProvider = sensorEventProvider;
        eventProcessors = Configs.getEventProcessors();
    }

    public void smartHomeProcess() {
        SensorEventType sensorEventType = SensorEventType.ALARM_ACTIVATE;
        sensorEventType.setCode("5");
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, "5");//sensorEventProvider.provideNextSensorEvent();

        while (sensorEvent != null) {
            processSensorEvent(sensorEvent);
            sensorEvent = sensorEventProvider.provideNextSensorEvent();
        }
    }

    public void processSensorEvent(SensorEvent sensorEvent) {
        System.out.println("Got event: " + sensorEvent);
        for (EventProcessor sensorEventProcessor : eventProcessors) {
            sensorEventProcessor.processSensorEvent(sensorEvent, smartHome);
        }
    }
}
