package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Configs;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeproviders.SmartHomeJSONProvider;
import ru.sbt.mipt.oop.homeproviders.SmartHomeProvider;
import ru.sbt.mipt.oop.remotecontrol.SmartHomeRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.commands.ActivateSignalizationCommand;
import ru.sbt.mipt.oop.remotecontrol.commands.AlarmSignalizationCommand;
import ru.sbt.mipt.oop.remotecontrol.commands.TurnOnLightsInHall;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeRemoteControlTest {
    SmartHome smartHome;
    SmartHomeRemoteControl smartHomeRemoteControl;

    @BeforeEach
    void setUp() {
        SmartHomeProvider smartHomeProvider = new SmartHomeJSONProvider(Configs.getFilenameWithSmartHome());
        smartHome = smartHomeProvider.provideSmartHome();
        smartHomeRemoteControl = new SmartHomeRemoteControl();
    }

    @Test
    void correctButton() {
        smartHomeRemoteControl.registerButtonToCommand("1", new AlarmSignalizationCommand(smartHome));
        smartHomeRemoteControl.registerButtonToCommand("2", new TurnOnLightsInHall(smartHome));
        assertNotNull(smartHomeRemoteControl.getCommand("1"));
        assertNotNull(smartHomeRemoteControl.getCommand("2"));
    }

    @Test
    void wrongButton() {
        smartHomeRemoteControl.registerButtonToCommand("11", new AlarmSignalizationCommand(smartHome));
        smartHomeRemoteControl.registerButtonToCommand("21", new TurnOnLightsInHall(smartHome));
        assertNull(smartHomeRemoteControl.getCommand("11"));
        assertNull(smartHomeRemoteControl.getCommand("21"));
    }
}