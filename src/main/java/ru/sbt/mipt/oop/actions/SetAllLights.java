package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.devices.Light;

public class SetAllLights implements Action {

    private boolean isOn;

    public SetAllLights(boolean isOn) {
        this.isOn = isOn;
    }

    @Override
    public void execute(Object object) {
        if (object instanceof Light){
            Light light = (Light) object;
            light.setOn(isOn);
        }
    }
}
