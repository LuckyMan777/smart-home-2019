package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.ActivateSignalization;

public class ActivateSignalizationCommand extends SmartHomeCommand {
    public ActivateSignalizationCommand(SmartHome smartHome, String code) {
        super(smartHome, new ActivateSignalization(code));
    }
}
