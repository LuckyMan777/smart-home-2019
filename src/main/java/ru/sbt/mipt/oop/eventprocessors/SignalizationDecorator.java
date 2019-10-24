package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.Signalization;

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
            }
            else if (checkSignalizationIsDeactivated(sensorEvent, smartHome) ||
                    (basicEventProcessor instanceof SignalizationDeactivatedEventProcessor))
                basicEventProcessor.processSensorEvent(sensorEvent, smartHome);
            if (sensorEvent.getType() == DOOR_CLOSED || sensorEvent.getType() == DOOR_OPEN ||
                    sensorEvent.getType() == LIGHT_OFF || sensorEvent.getType() == LIGHT_ON) {
                if (checkSignalizationIsActivated(sensorEvent, smartHome)) {
                    smartHome.execute(new Action() {
                        @Override
                        public void execute(Object object) {
                            if (object instanceof Signalization) {
                                Signalization signalization = (Signalization) object;
                                signalization.toAlarm();
                            }
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
        for (Actionable actionable : smartHome.getActionables()) {
            if (actionable instanceof Signalization) {
                return ((Signalization) actionable).isActivated();
            }
        }
        return false;
    }

    private boolean checkSignalizationIsDeactivated(SensorEvent sensorEvent, SmartHome smartHome) {
        for (Actionable actionable : smartHome.getActionables()) {
            if (actionable instanceof Signalization) {
                return ((Signalization) actionable).isDeactivated();
            }
        }
        return false;
    }

    private boolean checkSignalizationIsAlarmed(SensorEvent sensorEvent, SmartHome smartHome) {
        for (Actionable actionable : smartHome.getActionables()) {
            if (actionable instanceof Signalization) {
                return ((Signalization) actionable).isAlarmed();
            }
        }
        return false;
    }
}
