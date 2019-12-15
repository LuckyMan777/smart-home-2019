package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.devices.Signalization;

public class AlarmSignalization implements Action {
    @Override
    public void execute(Object object) {
        if (object instanceof Signalization){
            Signalization signalization = (Signalization) object;
            signalization.toAlarm();
        }
    }
}
