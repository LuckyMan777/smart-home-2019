package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.homeproviders.SmartHomeJSONProvider;
import ru.sbt.mipt.oop.homeproviders.SmartHomeProvider;

import java.util.Collection;

public class SmartHomeEventHandlerAdapter implements EventHandler {

    private Collection<EventProcessor> eventProcessors;
    private SmartHome smartHome;

    public SmartHomeEventHandlerAdapter() {
        SmartHomeProvider smartHomeProvider = new SmartHomeJSONProvider(Configs.getFilenameWithSmartHome());
        smartHome = smartHomeProvider.provideSmartHome();
        eventProcessors = Configs.getEventProcessors();
    }

    private SensorEvent getSensorEventFromCCSensorEvent(CCSensorEvent ccSensorEvent) {
        switch (ccSensorEvent.getEventType()) {
            case "LightIsOn":
                return new SensorEvent(SensorEventType.LIGHT_ON, ccSensorEvent.getObjectId());
            case "LightIsOff":
                return new SensorEvent(SensorEventType.LIGHT_OFF, ccSensorEvent.getObjectId());
            case "DoorIsOpen":
                return new SensorEvent(SensorEventType.DOOR_OPEN, ccSensorEvent.getObjectId());
            case "DoorIsClosed":
                return new SensorEvent(SensorEventType.DOOR_CLOSED, ccSensorEvent.getObjectId());
            default:
                return null;
        }
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = getSensorEventFromCCSensorEvent(event);
        if (sensorEvent == null) {
            return;
        }

        System.out.println("Got event: " + sensorEvent);
        for (EventProcessor sensorEventProcessor : eventProcessors) {
            sensorEventProcessor.processSensorEvent(sensorEvent, smartHome);
        }
    }
}
