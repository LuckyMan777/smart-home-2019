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
        SensorEvent sensorEvent = sensorEventProvider.provideNextSensorEvent();

        while (sensorEvent != null) {
            processSensorEvent(sensorEvent, smartHome);
            sensorEvent = sensorEventProvider.provideNextSensorEvent();
        }
    }

    private void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        System.out.println("Got event: " + sensorEvent);
        for (Room room : smartHome.getRooms()) {
            for (SmartDevice smartDevice : room.getSmartDevices()) {
                /*if (smartDevice.className.equals("ru.sbt.mipt.oop.Light"))
                    ((Light) smartDevice).updateState(sensorEvent, smartHome, room);
                if (smartDevice.className.equals("ru.sbt.mipt.oop.Door"))
                    ((Door) smartDevice).updateState(sensorEvent, smartHome, room);*/
                smartDevice.updateState(sensorEvent, smartHome, room);
            }
        }
    }
}
