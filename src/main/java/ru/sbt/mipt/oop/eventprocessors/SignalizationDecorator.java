package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Signalization;


public class SignalizationDecorator implements EventProcessor {
    EventProcessor basicEventProcessor;


    public SignalizationDecorator(EventProcessor basicEventProcessor) {
        this.basicEventProcessor = basicEventProcessor;
    }

    @Override
    public void processSensorEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        Signalization signalization = getSignalization(smartHome);
        if (signalization != null) {
            if (signalization.isDeactivated()) {
                basicEventProcessor.processSensorEvent(sensorEvent, smartHome);
                return;
            }
            if (signalization.isActivated()) {
                signalization.toAlarm();
            }
            if (signalization.isActivated() || signalization.isAlarmed()) {
                System.out.println("Sending sms");
            }
        } else {
            basicEventProcessor.processSensorEvent(sensorEvent, smartHome);
        }
    }

    @Override
    public boolean checkSensorEventIsCorrect(SensorEvent sensorEvent, SmartHome smartHome) {
        return true;
    }

    private Signalization getSignalization(SmartHome smartHome) {
        final Signalization[] signalization = new Signalization[1];
        smartHome.execute(object -> {
            if (object instanceof Signalization) {
                signalization[0] = (Signalization) object;
            }
        });
        return signalization[0];
    }
}
