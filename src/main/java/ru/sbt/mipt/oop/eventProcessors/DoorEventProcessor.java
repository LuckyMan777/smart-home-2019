package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Door;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements SensorEventProcessor {
    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (sensorEvent.getType() == DOOR_CLOSED || sensorEvent.getType() == DOOR_OPEN)
            smartHome.execute(object -> {
                if (object instanceof Door) {
                    Door door = (Door) object;
                    if (door.getId().equals(sensorEvent.getObjectId())) {
                        if (sensorEvent.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " was opened."); // " in room " + roomName + " was opened.");
                        }
                        if (sensorEvent.getType() == DOOR_CLOSED) {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " was closed."); // " in room " + roomName + " was closed.");
                        }
                    }
                }
            });
    }
}
