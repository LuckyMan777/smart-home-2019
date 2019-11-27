package ru.sbt.mipt.oop.factory;

import ru.sbt.mipt.oop.SensorEvent;

public interface StringSensorEventFactory {
    SensorEvent getSensorEvent(String eventType, String objectId);
}
