package ru.sbt.mipt.oop.devices;

import ru.sbt.mipt.oop.Actionable;

public class Light extends SmartDevice implements Actionable {
    private boolean isOn;

    public Light(String id, String roomName, boolean isOn) {
        super(id, roomName);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }
}
