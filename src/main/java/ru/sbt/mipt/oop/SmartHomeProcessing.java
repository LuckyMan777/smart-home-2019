package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHomeProcessing {
    private SmartHome smartHome;
    private SensorEventProvider sensorEventProvider;
    private Collection<SensorEventProcessor> sensorEventProcessors;

    public SmartHomeProcessing(SmartHome smartHome, SensorEventProvider sensorEventProvider) {
        this.smartHome = smartHome;
        this.sensorEventProvider = sensorEventProvider;
        sensorEventProcessors = new ArrayList<>();
        initialAddSensorEventProcessors();
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    private void initialAddSensorEventProcessors() {
        sensorEventProcessors.add(new LightEventProcessor());
        sensorEventProcessors.add(new DoorEventProcessor());
        sensorEventProcessors.add(new HallClosingEventProcessor());
    }

    public void smartHomeProcess() {
        SensorEvent sensorEvent = sensorEventProvider.provideNextSensorEvent();

        while (sensorEvent != null) {
            processSensorEvent(sensorEvent, smartHome);
            sensorEvent = sensorEventProvider.provideNextSensorEvent();
        }
    }

    private void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        System.out.println("Got event: " + sensorEvent);
        for (SensorEventProcessor sensorEventProcessor : sensorEventProcessors) {
            //System.out.println("Current processing: " + sensorEventProcessor.toString());
            sensorEventProcessor.processSensorEvent(sensorEvent, smartHome);
        }
    }
}
