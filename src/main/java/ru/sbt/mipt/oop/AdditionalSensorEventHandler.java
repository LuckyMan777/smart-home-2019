package ru.sbt.mipt.oop;

public class AdditionalSensorEventHandler implements SensorEventHandler {
    protected final String className;

    public AdditionalSensorEventHandler() {
        this.className = this.getClass().getName();
    }

    @Override
    public void handleSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {

    }
}
