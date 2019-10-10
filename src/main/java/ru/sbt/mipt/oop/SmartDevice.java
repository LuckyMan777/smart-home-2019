package ru.sbt.mipt.oop;

public class SmartDevice {
    protected final String id;

    public SmartDevice(String id) {
        this.id = id;
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    public String getId() {
        return id;
    }

    public void updateState(SensorEvent sensorEvent, SmartHome smartHome, Room room) {
    }
}
