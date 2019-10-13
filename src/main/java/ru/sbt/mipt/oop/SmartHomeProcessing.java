package ru.sbt.mipt.oop;

import java.io.IOException;

public class SmartHomeProcessing {
    private SmartHomeProvider smartHomeProvider;
    private SensorEventProvider sensorEventProvider;

    public SmartHomeProcessing(SmartHomeProvider smartHomeProvider, SensorEventProvider sensorEventProvider) {
        this.smartHomeProvider = smartHomeProvider;
        this.sensorEventProvider = sensorEventProvider;
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    public void smartHomeProcess() throws IOException, ClassNotFoundException {
        SmartHome smartHome = smartHomeProvider.provideSmartHome();
        SensorEvent sensorEvent = sensorEventProvider.provideNextSensorEvent();

        while (sensorEvent != null) {
            processSensorEvent(sensorEvent, smartHome);
            sensorEvent = sensorEventProvider.provideNextSensorEvent();
        }
    }

    private void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        System.out.println("Got event: " + sensorEvent);
        for (SensorEventHandler smartDevice : smartHome.getSmartDevices()) {
            smartDevice.handleSensorEvent(sensorEvent, smartHome);
        }
        for (AdditionalSensorEventHandler additionalSensorEventHandler : smartHome.getAdditionalSensorEventHandlers()) {
            additionalSensorEventHandler.handleSensorEvent(sensorEvent, smartHome);
        }
    }
}
