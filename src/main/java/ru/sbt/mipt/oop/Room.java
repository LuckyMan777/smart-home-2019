package ru.sbt.mipt.oop;

import java.util.Collection;

public class Room {
    private Collection<SmartDevice> smartDevices;
    private String name;

    public Room(Collection<SmartDevice> smartDevices, String name) {
        this.smartDevices = smartDevices;
        this.name = name;
    }

    public Collection<SmartDevice> getSmartDevices() {
        return smartDevices;
    }

    public String getName() {
        return name;
    }
}
