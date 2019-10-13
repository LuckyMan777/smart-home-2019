package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class Door extends SmartDevice {
    private boolean isOpen;

    public Door(String id, String roomName, boolean isOpen) {
        super(id, roomName);
        this.isOpen = isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

/*
    @Override
    public void updateState(SensorEvent sensorEvent, SmartHome smartHome, Room room) {
        super.updateState(sensorEvent, smartHome, room);
        if (getId().equals(sensorEvent.getObjectId())) {
            if (sensorEvent.getType() == DOOR_OPEN) {
                setOpen(true);
                System.out.println("Door " + getId() + " in room " + roomName + " was opened.");
            }
            if (sensorEvent.getType() == DOOR_CLOSED) {
                setOpen(false);
                System.out.println("Door " + getId() + " in room " + roomName + " was closed.");
                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                if (roomName.equals("hall")) {
                    processHallClosing(smartHome, room);
                }
            }
        }
    }
*/

    @Override
    public void handleSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (getId().equals(sensorEvent.getObjectId())) {
            if (sensorEvent.getType() == DOOR_OPEN) {
                setOpen(true);
                System.out.println("Door " + getId() + " in room " + roomName + " was opened.");
            }
            if (sensorEvent.getType() == DOOR_CLOSED) {
                setOpen(false);
                System.out.println("Door " + getId() + " in room " + roomName + " was closed.");
            }
        }
    }
}
