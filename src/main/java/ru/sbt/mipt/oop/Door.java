package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class Door extends SmartDevice {
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        super(id);
        this.isOpen = isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void updateState(SensorEvent sensorEvent, SmartHome smartHome, Room room) {
        super.updateState(sensorEvent, smartHome, room);
        if (getId().equals(sensorEvent.getObjectId())) {
            if (sensorEvent.getType() == DOOR_OPEN) {
                setOpen(true);
                System.out.println("Door " + getId() + " in room " + room.getName() + " was opened.");
            }
            if (sensorEvent.getType() == DOOR_CLOSED) {
                setOpen(false);
                System.out.println("Door " + getId() + " in room " + room.getName() + " was closed.");
                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                if (room.getName().equals("hall")) {
                    processHallClosing(smartHome, room);
                }
            }
        }
    }

    private void processHallClosing(SmartHome smartHome, Room room) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (SmartDevice smartDevice : homeRoom.getSmartDevices()) {
                if (smartDevice instanceof Light) {
                    ((Light) smartDevice).setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, smartDevice.getId());
                    sendCommand(command);
                }
            }
        }
    }
}
