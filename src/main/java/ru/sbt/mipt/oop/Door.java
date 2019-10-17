package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class Door extends SmartDevice implements Actionable {
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        super(id);
        this.isOpen = isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public void execute(Action action) {
        if (getId().equals(action.getObjectId())) {
            if (action.getCommand() == DOOR_OPEN) {
                setOpen(true);
                System.out.println("Door " + getId() + " was opened."); // " in room " + roomName + " was opened.");
            }
            if (action.getCommand() == DOOR_CLOSED) {
                setOpen(false);
                System.out.println("Door " + getId() + " was closed."); // " in room " + roomName + " was closed.");
            }
        }
    }
}
