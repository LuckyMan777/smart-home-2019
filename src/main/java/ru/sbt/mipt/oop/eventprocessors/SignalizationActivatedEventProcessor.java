package ru.sbt.mipt.oop.eventprocessors;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Signalization;

@Component
public class SignalizationActivatedEventProcessor implements EventProcessor {

    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (checkSensorEventIsCorrect(sensorEvent, smartHome)) {
            smartHome.execute(object -> {
                if (object instanceof Signalization)
                    ((Signalization) object).activate(sensorEvent.getType().getCode());
            });
        }
    }

    @Override
    public boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        return sensorEvent.getType() == SensorEventType.ALARM_ACTIVATE;
    }
}
