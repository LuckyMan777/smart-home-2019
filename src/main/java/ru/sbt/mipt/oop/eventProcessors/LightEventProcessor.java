package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Light;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {
    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (checkSensorEventIsCorrect(sensorEvent, smartHome))
            smartHome.execute(new Action() {
                @Override
                public void execute(Object object) {
                    if (object instanceof Light) {
                        Light light = (Light) object;
                        if (light.getId().equals(sensorEvent.getObjectId())) {
                            if (sensorEvent.getType() == LIGHT_ON) {
                                light.setOn(true);
                                System.out.println("Light " + light.getId() + " was turned on.");  // " in room " + roomName + " was turned on.");
                            }
                            if (sensorEvent.getType() == LIGHT_OFF) {
                                light.setOn(false);
                                System.out.println("Light " + light.getId() + " was turned off.");  // " in room " + roomName + " was turned off.");
                            }
                        }
                    }
                }
            });
    }

    @Override
    public boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        return sensorEvent.getType() == LIGHT_OFF || sensorEvent.getType() == LIGHT_ON;
    }
}
