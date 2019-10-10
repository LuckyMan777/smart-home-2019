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

    public void smartHomeProcess() throws IOException {
        SmartHome smartHome = smartHomeProvider.provideSmartHome();
        SensorEvent sensorEvent;

        do {
            sensorEvent = sensorEventProvider.provideNextSensorEvent();
            processSensorEvent(sensorEvent, smartHome);
        } while (sensorEvent != null);
    }

    private void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        System.out.println("Got event: " + sensorEvent);
        for (Room room : smartHome.getRooms()) {
            for (SmartDevice smartDevice : room.getSmartDevices()) {
                smartDevice.updateState(sensorEvent, smartHome, room);
            }
        }
    }
}
