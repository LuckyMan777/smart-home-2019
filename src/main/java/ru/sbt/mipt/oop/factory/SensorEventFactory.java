package ru.sbt.mipt.oop.factory;

import ru.sbt.mipt.oop.SensorEvent;

public interface SensorEventFactory {
    SensorEvent getSensorEvent(String objectId);
}
