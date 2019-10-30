package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Actionable> actionables;

    public SmartHome() { actionables = new ArrayList<>();
    }

    public SmartHome(Collection<Actionable> actiobables) {
        this.actionables = actiobables;
    }

    public void addActionable(Actionable actionable) {
        actionables.add(actionable);
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        for (Actionable room : rooms) {
            room.execute(action);
        }
    }
}
