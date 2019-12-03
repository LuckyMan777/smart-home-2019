package ru.sbt.mipt.oop.factory;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

public class LightOnSensorEventFactory implements SensorEventFactory {
    @Override
    public SensorEvent getSensorEvent(String objectId) {
        return new SensorEvent(SensorEventType.LIGHT_ON, objectId);
    }
}
