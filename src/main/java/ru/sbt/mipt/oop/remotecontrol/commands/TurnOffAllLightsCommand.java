package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.SetAllLights;

public class TurnOffAllLightsCommand extends SmartHomeCommand {
    public TurnOffAllLightsCommand(SmartHome smartHome) {
        super(smartHome, new SetAllLights(false));
    }
}
