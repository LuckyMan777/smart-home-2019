package ru.sbt.mipt.oop;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Actionable> smartDevices;
    private String name;

    public Room(Collection<Actionable> smartDevices, String name) {
        this.smartDevices = smartDevices;
        this.name = name;
    }

    public Collection<Actionable> getSmartDevices() {
        return smartDevices;
    }

    public void addSmartDevice(Actionable smartDevice) {
        smartDevices.add(smartDevice);
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        for (Actionable smartDevice : smartDevices) {
            smartDevice.execute(action);
        }
    }
}
