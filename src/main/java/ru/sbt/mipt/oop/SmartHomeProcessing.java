package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SmartHomeProcessing {
    private SmartHomeProvider smartHomeProvider;
    private SensorEventProvider sensorEventProvider;

    public SmartHomeProcessing(SmartHomeProvider smartHomeProvider, SensorEventProvider sensorEventProvider) {
        this.smartHomeProvider = smartHomeProvider;
        this.sensorEventProvider = sensorEventProvider;
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    public void smartHomeProcess() throws IOException {
        SmartHome smartHome = smartHomeProvider.provideSmartHome();
        SensorEvent sensorEvent;

        do {
            sensorEvent = sensorEventProvider.provideNextSensorEvent();
            processSensorEvent(sensorEvent, smartHome);
        } while (sensorEvent != null);
    }

    private void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        System.out.println("Got event: " + sensorEvent);
        for (Room room : smartHome.getRooms()) {
            for (SmartDevice smartDevice : room.getSmartDevices()) {
                smartDevice.updateState(sensorEvent, smartHome, room);
            }
        }
    }
        /*
        if (sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF) {
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    processLight(sensorEvent, room, light);
                }
            }
        }
        if (sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    processDoor(sensorEvent, smartHome, room, door);
                }
            }
        }*/
    /*
    private void processLight(SensorEvent sensorEvent, Room room, Light light) {
        if (light.getId().equals(sensorEvent.getObjectId())) {
            if (sensorEvent.getType() == LIGHT_ON) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }

    private void processDoor(SensorEvent sensorEvent, SmartHome smartHome, Room room, Door door) {
        if (door.getId().equals(sensorEvent.getObjectId())) {
            if (sensorEvent.getType() == DOOR_OPEN) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            } else {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
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
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }*/
}
