package ru.sbt.mipt.oop;

import java.io.IOException;

public interface SensorEventProvider {
    SensorEvent provideNextSensorEvent();
}
