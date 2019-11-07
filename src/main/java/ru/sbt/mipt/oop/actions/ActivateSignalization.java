package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.devices.Signalization;

public class ActivateSignalization implements Action {

    private String code;

    public ActivateSignalization(String code) {
        this.code = code;
    }

    @Override
    public void execute(Object object) {
        if (object instanceof Signalization){
            Signalization signalization = (Signalization) object;
            signalization.activate(code);
        }
    }
}
