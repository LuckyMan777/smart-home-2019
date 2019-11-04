package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<SmartDevice> smartDevices;
    private String name;

    public Room(Collection<SmartDevice> smartDevices, String name) {
        this.smartDevices = smartDevices;
        this.name = name;
    }

    public void addSmartDevice(SmartDevice smartDevice) {
        smartDevices.add(smartDevice);
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        for (Actionable smartDevice : smartDevices) {
            smartDevice.execute(action);
        }
    }
}
