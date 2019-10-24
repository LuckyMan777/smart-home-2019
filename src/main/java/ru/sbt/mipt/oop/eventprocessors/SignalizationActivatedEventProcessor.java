package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Signalization;

public class SignalizationActivatedEventProcessor implements EventProcessor {

    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (checkSensorEventIsCorrect(sensorEvent, smartHome)) {
            smartHome.execute(new Action() {
                @Override
                public void execute(Object object) {
                    if (object instanceof Signalization)
                        ((Signalization) object).activate(sensorEvent.getType().getCode());
                }
            });
        }
    }

    @Override
    public boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        return sensorEvent.getType() == SensorEventType.ALARM_ACTIVATE;
    }
}
