package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Door;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (checkSensorEventIsCorrect(sensorEvent, smartHome))
            smartHome.execute(new Action() {
                @Override
                public void execute(Object object) {
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
                }
            });
    }

    @Override
    public boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        return sensorEvent.getType() == DOOR_CLOSED || sensorEvent.getType() == DOOR_OPEN;
    }

}
