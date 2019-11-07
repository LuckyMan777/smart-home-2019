package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.SetAllLightsInRoom;

public class TurnOnLightsInHall extends SmartHomeCommand {
    public TurnOnLightsInHall(SmartHome smartHome) {
        super(smartHome, new SetAllLightsInRoom(true, "hall"));
    }
}
