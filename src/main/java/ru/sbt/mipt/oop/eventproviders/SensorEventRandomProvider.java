package ru.sbt.mipt.oop.eventproviders;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

public class SensorEventRandomProvider implements SensorEventProvider {

    @Override
    public SensorEvent provideNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.1) return null; // null means end of event stream
        int countEventTypes = SensorEventType.values().length;
        SensorEventType sensorEventType = SensorEventType.values()[(int) (countEventTypes * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
