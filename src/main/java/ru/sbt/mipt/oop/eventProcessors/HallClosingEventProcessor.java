package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commandsenders.CommandSender;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;

import java.util.concurrent.atomic.AtomicBoolean;

// если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
// в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
public class HallClosingEventProcessor implements EventProcessor {

    private CommandSender commandSender;

    public HallClosingEventProcessor(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (checkSensorEventIsCorrect(sensorEvent, smartHome)) {
            smartHome.execute(object -> {
                if (object instanceof Door) {
                    Door door = (Door) object;
                    if (door.getId().equals(sensorEvent.getObjectId())) {
                        door.setOpen(false);
                    }
                } else if (object instanceof Light) {
                    Light light = (Light) object;
                    ((Light) object).setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    commandSender.sendCommand(command);
                }
            });
        }
    }

    @Override
    public boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        final boolean[] correct = {false};
        if (sensorEvent.getType() == SensorEventType.DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Door) {
                    Door door = (Door) object;
                    if (door.getRoomName().equals("hall") && door.getId().equals(sensorEvent.getObjectId())) {
                        correct[0] = true;
                    }
                }
            });
        }
        return correct[0];
    }
}
