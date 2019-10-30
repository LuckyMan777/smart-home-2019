package ru.sbt.mipt.oop.devices;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public abstract class SmartDevice implements Actionable {
    protected final String id;
    protected final String className;
    protected final String roomName;

    public SmartDevice(String id, String roomName) {
        this.id = id;
        this.className = this.getClass().getName();
        this.roomName = roomName;
    }

    public String getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
