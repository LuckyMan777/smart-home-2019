package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Actionable> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Actionable> smartDevices) {
        this.rooms = smartDevices;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Actionable> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        for (Actionable room : rooms) {
            room.execute(action);
        }
    }
}
