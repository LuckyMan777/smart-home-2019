package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface EventProcessor {
    void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome);
    boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome);
}