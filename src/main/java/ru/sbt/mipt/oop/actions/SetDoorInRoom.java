package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.devices.Door;

public class SetDoorInRoom implements Action {
    private boolean isOpen;
    private String roomName;

    public SetDoorInRoom(boolean isOpen, String roomName) {
        this.isOpen = isOpen;
        this.roomName = roomName;
    }

    @Override
    public void execute(Object object) {
        if (object instanceof Door) {
            Door door = (Door) object;
            if (door.getRoomName().equals(roomName)) {
                door.setOpen(isOpen);
            }
        }
    }
}
