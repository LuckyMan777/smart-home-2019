package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Signalization;

import java.util.concurrent.atomic.AtomicBoolean;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SignalizationDecorator implements EventProcessor {
    EventProcessor basicEventProcessor;

    public SignalizationDecorator(EventProcessor basicEventProcessor) {
        this.basicEventProcessor = basicEventProcessor;
    }

    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (basicEventProcessor.checkSensorEventIsCorrect(sensorEvent, smartHome)) {
            if (checkSignalizationIsAlarmed(sensorEvent, smartHome)) {
                System.out.println("Sending sms");
            } else if (checkSignalizationIsDeactivated(sensorEvent, smartHome) ||
                    (basicEventProcessor instanceof SignalizationDeactivatedEventProcessor))
                basicEventProcessor.processSensorEvent(sensorEvent, smartHome);
            if (sensorEvent.getType() == DOOR_CLOSED || sensorEvent.getType() == DOOR_OPEN ||
                    sensorEvent.getType() == LIGHT_OFF || sensorEvent.getType() == LIGHT_ON) {
                if (checkSignalizationIsActivated(sensorEvent, smartHome)) {
                    smartHome.execute(object -> {
                        if (object instanceof Signalization) {
                            Signalization signalization = (Signalization) object;
                            signalization.toAlarm();
                        }
                    });
                    System.out.println("Sending sms");
                }
            }
        }
    }

    @Override
    public boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        return true;
    }

    private boolean checkSignalizationIsActivated(SensorEvent sensorEvent, SmartHome smartHome) {
        AtomicBoolean isActivated = new AtomicBoolean(false);
        smartHome.execute(object -> {
            if (object instanceof Signalization) {
                isActivated.set(((Signalization) object).isActivated());
            }
        });
        return isActivated.get();
    }

    private boolean checkSignalizationIsDeactivated(SensorEvent sensorEvent, SmartHome smartHome) {
        AtomicBoolean isDeactivated = new AtomicBoolean(false);
        smartHome.execute(object -> {
            if (object instanceof Signalization) {
                isDeactivated.set(((Signalization) object).isDeactivated());
            }
        });
        return isDeactivated.get();
    }

    private boolean checkSignalizationIsAlarmed(SensorEvent sensorEvent, SmartHome smartHome) {
        AtomicBoolean isAlarmed = new AtomicBoolean(false);
        smartHome.execute(object -> {
            if (object instanceof Signalization) {
                isAlarmed.set(((Signalization) object).isAlarmed());
            }
        });
        return isAlarmed.get();
    }
}
