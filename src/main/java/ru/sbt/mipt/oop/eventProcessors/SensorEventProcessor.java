package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface SensorEventProcessor {
    void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome);
}