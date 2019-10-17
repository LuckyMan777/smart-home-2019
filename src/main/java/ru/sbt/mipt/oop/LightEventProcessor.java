package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements SensorEventProcessor {
    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (sensorEvent.getType() == LIGHT_OFF || sensorEvent.getType() == LIGHT_ON)
            smartHome.execute(new Action(sensorEvent.getType(), sensorEvent.getObjectId()));
    }
}
