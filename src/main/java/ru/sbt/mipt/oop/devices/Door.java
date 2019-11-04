package ru.sbt.mipt.oop.devices;

import ru.sbt.mipt.oop.Actionable;

public class Door extends SmartDevice implements Actionable {
    private boolean isOpen;

    public Door(String id, String roomName, boolean isOpen) {
        super(id, roomName);
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
