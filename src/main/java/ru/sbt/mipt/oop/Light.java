package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light extends SmartDevice {
    private boolean isOn;

    public Light(String id, boolean isOn) {
        super(id);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void updateState(SensorEvent sensorEvent, SmartHome smartHome, Room room) {
        super.updateState(sensorEvent, smartHome, room);
        if (getId().equals(sensorEvent.getObjectId())) {
            if (sensorEvent.getType() == LIGHT_ON) {
                setOn(true);
                System.out.println("Light " + getId() + " in room " + room.getName() + " was turned on.");
            }
            if (sensorEvent.getType() == LIGHT_OFF){
                setOn(false);
                System.out.println("Light " + getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
}
