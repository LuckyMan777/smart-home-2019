package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.SetAllLights;

public class TurnOnAllLightsCommand extends SmartHomeCommand {
    public TurnOnAllLightsCommand(SmartHome smartHome) {
        super(smartHome, new SetAllLights(true));
    }
}