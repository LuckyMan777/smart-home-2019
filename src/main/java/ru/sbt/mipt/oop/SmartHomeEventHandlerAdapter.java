package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.SignalizationDecorator;
import ru.sbt.mipt.oop.factory.SensorEventFactory;

import java.util.Map;

public class SmartHomeEventHandlerAdapter implements EventHandler {

    private SmartHome smartHome;
    private EventProcessor signalizationDecorator;
    private Map<String, SensorEventFactory> stringToSensorEventFactoryMap;

    public SmartHomeEventHandlerAdapter(SmartHome smartHome,
                                        SignalizationDecorator signalizationDecorator,
                                        Map<String, SensorEventFactory> stringToSensorEventFactoryMap) {
        this.smartHome = smartHome;
        this.signalizationDecorator = signalizationDecorator;
        this.stringToSensorEventFactoryMap = stringToSensorEventFactoryMap;
    }

    private SensorEvent convertEvent(CCSensorEvent sensorEvent) {
        if (stringToSensorEventFactoryMap.containsKey(sensorEvent.getEventType())) {
            return stringToSensorEventFactoryMap.get(sensorEvent.getEventType()).getSensorEvent(sensorEvent.getObjectId());
        } else {
            return null;
        }
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = convertEvent(event);
        if (sensorEvent == null) {
            return;
        }
        signalizationDecorator.processSensorEvent(sensorEvent, smartHome);
    }
}
