package ru.sbt.mipt.oop.eventprocessors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commandsenders.CommandSender;
import ru.sbt.mipt.oop.commandsenders.CommandType;
import ru.sbt.mipt.oop.commandsenders.SensorCommand;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;

// если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
// в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
@Component
public class HallClosingEventProcessor implements EventProcessor {

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
                    CommandSender.sendCommand(command);
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
