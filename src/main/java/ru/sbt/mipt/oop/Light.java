package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light extends SmartDevice implements Actionable {
    private boolean isOn;

    public Light(String id, boolean isOn) {
        super(id);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    @Override
    public void execute(Action action) {
        if (getId().equals(action.getObjectId())) {
            if (action.getCommand() == LIGHT_ON) {
                setOn(true);
                System.out.println("Light " + getId() + " was turned on.");  // " in room " + roomName + " was turned on.");
            }
            if (action.getCommand() == LIGHT_OFF) {
                setOn(false);
                System.out.println("Light " + getId() + " was turned off.");  // " in room " + roomName + " was turned off.");
            }
        }
    }
}
