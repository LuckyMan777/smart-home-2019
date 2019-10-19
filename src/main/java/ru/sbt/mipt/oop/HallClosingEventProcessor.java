package ru.sbt.mipt.oop;

// если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
// в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
public class HallClosingEventProcessor implements SensorEventProcessor {

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (checkSensorEventIsCorrect(sensorEvent, smartHome)) {
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    for (SmartDevice smartDevice : room.getSmartDevices()) {
                        smartDevice.execute(new Action() {
                            @Override
                            void execute(Object object) {
                                if (object instanceof Light) {
                                    Light light = (Light) object;
                                    light.setOn(false);
                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, smartDevice.getId());
                                    sendCommand(command);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        if (sensorEvent.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    for (SmartDevice smartDevice : room.getSmartDevices()) {
                        if ((smartDevice instanceof Door) && smartDevice.id.equals(sensorEvent.getObjectId()))
                            return true;
                    }
                }
            }
        }
        return false;
    }
}
