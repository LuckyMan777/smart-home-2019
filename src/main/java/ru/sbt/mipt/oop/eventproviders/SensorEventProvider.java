package ru.sbt.mipt.oop.eventproviders;

import ru.sbt.mipt.oop.SensorEvent;

public interface SensorEventProvider {
    SensorEvent provideNextSensorEvent();
}
