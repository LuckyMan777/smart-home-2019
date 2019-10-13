package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light extends SmartDevice {
    private boolean isOn;

    public Light(String id, String roomName, boolean isOn) {
        super(id, roomName);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

/*
    @Override
    public void updateState(SensorEvent sensorEvent, SmartHome smartHome, Room room) {
        super.updateState(sensorEvent, smartHome, room);
        if (getId().equals(sensorEvent.getObjectId())) {
            if (sensorEvent.getType() == LIGHT_ON) {
                setOn(true);
                System.out.println("Light " + getId() + " in room " + room.getName() + " was turned on.");
            }
            if (sensorEvent.getType() == LIGHT_OFF) {
                setOn(false);
                System.out.println("Light " + getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
*/

    @Override
    public void handleSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (getId().equals(sensorEvent.getObjectId())) {
            if (sensorEvent.getType() == LIGHT_ON) {
                setOn(true);
                System.out.println("Light " + getId() + " in room " + roomName + " was turned on.");
            }
            if (sensorEvent.getType() == LIGHT_OFF) {
                setOn(false);
                System.out.println("Light " + getId() + " in room " + roomName + " was turned off.");
            }
        }
    }
}
