package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Configs;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeproviders.SmartHomeJSONProvider;
import ru.sbt.mipt.oop.homeproviders.SmartHomeProvider;
import ru.sbt.mipt.oop.remotecontrol.commands.ActivateSignalizationCommand;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeCommandTest {

    private SmartHome smartHome;

    @BeforeEach
    void setUp() {
        SmartHomeProvider smartHomeProvider = new SmartHomeJSONProvider(Configs.getFilenameWithSmartHome());
        smartHome = smartHomeProvider.provideSmartHome();
    }

    @Test
    void activateSignalization(){
        (new ActivateSignalizationCommand(smartHome, "1234")).execute();
        TestUtils.
    }
}