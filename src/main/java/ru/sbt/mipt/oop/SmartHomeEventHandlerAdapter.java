package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.SignalizationDecorator;
import ru.sbt.mipt.oop.factory.StringSensorEventFactory;

public class SmartHomeEventHandlerAdapter implements EventHandler {

    private SmartHome smartHome;
    private StringSensorEventFactory stringSensorEventFactory;
    private EventProcessor signalizationDecorator;

    public SmartHomeEventHandlerAdapter(SmartHome smartHome,
                                        StringSensorEventFactory stringSensorEventFactory,
                                        SignalizationDecorator signalizationDecorator) {
        this.smartHome = smartHome;
        this.stringSensorEventFactory = stringSensorEventFactory;
        this.signalizationDecorator = signalizationDecorator;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = stringSensorEventFactory.getSensorEvent(event.getEventType(), event.getObjectId());
        if (sensorEvent == null) {
            return;
        }
        signalizationDecorator.processSensorEvent(sensorEvent, smartHome);
    }
}
