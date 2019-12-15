package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SmartHome;

public class SmartHomeCommand implements Command {
    private SmartHome smartHome;
    private Action action;

    public SmartHomeCommand(SmartHome smartHome, Action action) {
        this.smartHome = smartHome;
        this.action = action;
    }

    @Override
    public void execute() {
        smartHome.execute(action);
    }
}
