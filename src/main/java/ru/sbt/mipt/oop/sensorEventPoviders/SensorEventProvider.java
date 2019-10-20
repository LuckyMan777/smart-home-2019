package ru.sbt.mipt.oop.sensorEventPoviders;

import ru.sbt.mipt.oop.SensorEvent;

public interface SensorEventProvider {
    SensorEvent provideNextSensorEvent();
}
