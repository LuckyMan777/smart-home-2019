package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.SetDoorInRoom;

public class CloseHallDoorCommand extends SmartHomeCommand {
    public CloseHallDoorCommand(SmartHome smartHome) {
        super(smartHome, new SetDoorInRoom(false, "hall"));
    }
}
