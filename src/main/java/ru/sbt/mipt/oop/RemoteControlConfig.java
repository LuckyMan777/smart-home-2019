package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.SmartHomeRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.commands.*;


@Configuration
public class RemoteControlConfig {
    private static final String RC_ID = "1234";
    private static final String CODE = "1234";

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Bean
    RemoteControl remoteControl(SmartHome smartHome) {
        SmartHomeRemoteControl smartHomeRemoteControl = new SmartHomeRemoteControl();
        smartHomeRemoteControl.registerButtonToCommand("1", activateSignalizationCommand(smartHome));
        smartHomeRemoteControl.registerButtonToCommand("A", alarmSignalizationCommand(smartHome));
        smartHomeRemoteControl.registerButtonToCommand("2", closeHallDoorCommand(smartHome));
        smartHomeRemoteControl.registerButtonToCommand("B", turnOffAllLightsCommand(smartHome));
        smartHomeRemoteControl.registerButtonToCommand("3", turnOnAllLightsCommand(smartHome));
        smartHomeRemoteControl.registerButtonToCommand("C", turnOnLightsInHallCommand(smartHome));
        remoteControlRegistry().registerRemoteControl(smartHomeRemoteControl, RC_ID);
        return smartHomeRemoteControl;
    }

    @Bean
    Command activateSignalizationCommand(SmartHome smartHome) {
        return new ActivateSignalizationCommand(smartHome, CODE);
    }

    @Bean
    Command alarmSignalizationCommand(SmartHome smartHome) {
        return new AlarmSignalizationCommand(smartHome);
    }

    @Bean
    Command closeHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    Command turnOnAllLightsCommand(SmartHome smartHome) {
        return new TurnOnAllLightsCommand(smartHome);
    }

    @Bean
    Command turnOffAllLightsCommand(SmartHome smartHome) {
        return new TurnOffAllLightsCommand(smartHome);
    }

    @Bean
    Command turnOnLightsInHallCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }
}
