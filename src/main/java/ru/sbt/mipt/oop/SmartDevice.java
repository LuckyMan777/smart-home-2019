package ru.sbt.mipt.oop;

public class SmartDevice implements SensorEventHandler {
    protected final String id;
    protected final String roomName;
    protected final String className;

    public SmartDevice(String id, String roomName) {
        this.id = id;
        this.roomName = roomName;
        this.className = this.getClass().getName();
    }

    public String getId() {
        return id;
    }

    @Override
    public void handleSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
    }
}
