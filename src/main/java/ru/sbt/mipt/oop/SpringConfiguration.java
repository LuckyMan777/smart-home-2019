package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.SignalizationDecorator;
import ru.sbt.mipt.oop.eventprocessors.SmartHomeEventProcessor;
import ru.sbt.mipt.oop.factory.*;
import ru.sbt.mipt.oop.homeproviders.SmartHomeJSONProvider;
import ru.sbt.mipt.oop.homeproviders.SmartHomeProvider;
import ru.sbt.mipt.oop.remotecontrol.SmartHomeRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.commands.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan
public class SpringConfiguration {
    private static final String RC_ID = "1234";
    private static final String CODE = "1234";

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
        return new SmartHomeEventHandlerAdapter(smartHome(), signalizationDecorator(), getStringToSensorEventFactoryMap());
    }

    @Bean
    Map<String, SensorEventFactory> getStringToSensorEventFactoryMap() {
        Map<String, SensorEventFactory> stringToSensorEventFactoryMap = new HashMap<>();
        stringToSensorEventFactoryMap.put("LightIsOn", new LightOnSensorEventFactory());
        stringToSensorEventFactoryMap.put("LightIsOff", new LightOffSensorEventFactory());
        stringToSensorEventFactoryMap.put("DoorIsOpen", new DoorOpenSensorEventFactory());
        stringToSensorEventFactoryMap.put("DoorIsClosed", new DoorClosedSensorEventFactory());
        return stringToSensorEventFactoryMap;
    }

    @Bean
    SignalizationDecorator signalizationDecorator() {
        return new SignalizationDecorator(smartHomeEventProcessor());
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

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Bean
    RemoteControl remoteControl(SmartHome smartHome) {
        SmartHomeRemoteControl smartHomeRemoteControl = new SmartHomeRemoteControl();
        smartHomeRemoteControl.registerButtonToCommand("1", activateSignalizationCommand());
        smartHomeRemoteControl.registerButtonToCommand("A", alarmSignalizationCommand());
        smartHomeRemoteControl.registerButtonToCommand("2", closeHallDoorCommand());
        smartHomeRemoteControl.registerButtonToCommand("B", turnOffAllLightsCommand());
        smartHomeRemoteControl.registerButtonToCommand("3", turnOnAllLightsCommand());
        smartHomeRemoteControl.registerButtonToCommand("C", turnOnLightsInHallCommand());
        remoteControlRegistry().registerRemoteControl(smartHomeRemoteControl, RC_ID);
        return smartHomeRemoteControl;
    }

    @Bean
    Command activateSignalizationCommand() {
        return new ActivateSignalizationCommand(smartHome(), CODE);
    }

    @Bean
    Command alarmSignalizationCommand() {
        return new AlarmSignalizationCommand(smartHome());
    }

    @Bean
    Command closeHallDoorCommand() {
        return new CloseHallDoorCommand(smartHome());
    }

    @Bean
    Command turnOnAllLightsCommand() {
        return new TurnOnAllLightsCommand(smartHome());
    }

    @Bean
    Command turnOffAllLightsCommand() {
        return new TurnOffAllLightsCommand(smartHome());
    }

    @Bean
    Command turnOnLightsInHallCommand() {
        return new CloseHallDoorCommand(smartHome());
    }


}
