package ru.sbt.mipt.oop;

public class Door extends SmartDevice implements Actionable {
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        super(id);
        this.isOpen = isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
