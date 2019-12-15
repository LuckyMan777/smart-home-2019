package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.AlarmSignalization;

public class AlarmSignalizationCommand extends SmartHomeCommand {
    public AlarmSignalizationCommand(SmartHome smartHome) {
        super(smartHome, new AlarmSignalization());
    }
}
