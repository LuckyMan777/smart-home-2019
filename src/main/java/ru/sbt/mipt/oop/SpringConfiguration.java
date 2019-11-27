package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.SignalizationDecorator;
import ru.sbt.mipt.oop.eventprocessors.SmartHomeEventProcessor;
import ru.sbt.mipt.oop.factory.CCStringSensorEventFactory;
import ru.sbt.mipt.oop.homeproviders.SmartHomeJSONProvider;
import ru.sbt.mipt.oop.homeproviders.SmartHomeProvider;

import java.util.Collection;

@Configuration
@ComponentScan
public class SpringConfiguration {
    @Autowired
    private Collection<EventProcessor> eventProcessors;

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(smartHomeEventHandlerAdapter());
        return sensorEventsManager;
    }

    @Bean
    SmartHomeEventHandlerAdapter smartHomeEventHandlerAdapter() {
        return new SmartHomeEventHandlerAdapter(smartHome(),
                ccStringSensorEventFactory(), signalizationDecorator());
    }

    @Bean
    SignalizationDecorator signalizationDecorator(){
        return new SignalizationDecorator(smartHomeEventProcessor());
    }

    @Bean
    CCStringSensorEventFactory ccStringSensorEventFactory() {
        return new CCStringSensorEventFactory();
    }

    @Bean
    SmartHomeEventProcessor smartHomeEventProcessor() {
        return new SmartHomeEventProcessor(eventProcessors);
    }

    @Bean
    SmartHome smartHome() {
        SmartHomeProvider smartHomeProvider = new SmartHomeJSONProvider(Configs.getFilenameWithSmartHome());
        SmartHome smartHome = smartHomeProvider.provideSmartHome();
        if (smartHome == null) throw new RuntimeException("Couldn't load SmartHome");
        return smartHome;
    }
}
