package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements SensorEventProcessor {
    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (sensorEvent.getType() == DOOR_CLOSED || sensorEvent.getType() == DOOR_OPEN)
            smartHome.execute(new Action(sensorEvent.getType(), sensorEvent.getObjectId()));
    }
}
