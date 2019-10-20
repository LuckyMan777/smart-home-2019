package ru.sbt.mipt.oop.smartDevices;

import ru.sbt.mipt.oop.Actionable;

public class Door extends SmartDevice implements Actionable {
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        super(id);
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
