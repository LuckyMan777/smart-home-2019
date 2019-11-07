package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Room;

public class SetAllLightsInRoom implements Action {

    private String roomName;
    private boolean isOn;

    public SetAllLightsInRoom(boolean isOn, String room) {
        this.roomName = room;
        this.isOn = isOn;
    }

    @Override
    public void execute(Object object) {
        if (object instanceof Room){
            Room room = (Room) object;
            if (room.getName().equals(roomName))
            {
                room.execute(new SetAllLights(isOn));
            }
        }
    }
}
