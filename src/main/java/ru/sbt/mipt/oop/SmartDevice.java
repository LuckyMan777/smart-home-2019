package ru.sbt.mipt.oop;

public abstract class SmartDevice {
    protected final String id;
    protected final String className;

    public SmartDevice(String id) {
        this.id = id;
        this.className = this.getClass().getName();
    }

    public String getId() {
        return id;
    }
}
