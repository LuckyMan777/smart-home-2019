package ru.sbt.mipt.oop.factory;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

public class CCStringSensorEventFactory implements StringSensorEventFactory {

    @Override
    public SensorEvent getSensorEvent(String eventType, String objectId) {
        switch (eventType) {
            case "LightIsOn":
                return new SensorEvent(SensorEventType.LIGHT_ON, objectId);
            case "LightIsOff":
                return new SensorEvent(SensorEventType.LIGHT_OFF, objectId);
            case "DoorIsOpen":
                return new SensorEvent(SensorEventType.DOOR_OPEN, objectId);
            case "DoorIsClosed":
                return new SensorEvent(SensorEventType.DOOR_CLOSED, objectId);
            default:
                return null;
        }
    }
}
