package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Configs;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Signalization;
import ru.sbt.mipt.oop.homeproviders.SmartHomeJSONProvider;
import ru.sbt.mipt.oop.homeproviders.SmartHomeProvider;
import ru.sbt.mipt.oop.remotecontrol.commands.*;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeCommandTest {

    private SmartHome smartHome;

    @BeforeEach
    void setUp() {
        SmartHomeProvider smartHomeProvider = new SmartHomeJSONProvider(Configs.getFilenameWithSmartHome());
        smartHome = smartHomeProvider.provideSmartHome();
    }

    @Test
    void testActivateSignalizationCommand(){
        (new ActivateSignalizationCommand(smartHome, "1234")).execute();
        Signalization signalization = TestUtils.getSignalization(smartHome);
        assertTrue(signalization.isActivated());
    }

    @Test
    void testAlarmSignalizationCommand(){
        (new AlarmSignalizationCommand(smartHome)).execute();
        Signalization signalization = TestUtils.getSignalization(smartHome);
        assertTrue(signalization.isAlarmed());
    }

    @Test
    void testCloseHallDoorCommand(){
        (new CloseHallDoorCommand(smartHome)).execute();
        smartHome.execute(new TestUtils.CheckDoorStateInRoom(false, "hall"));
    }

    @Test
    void testTurnOffAllLightsCommand(){
        (new TurnOffAllLightsCommand(smartHome)).execute();
        smartHome.execute(new TestUtils.CheckAllLightStates(false));
    }

    @Test
    void testTurnOnAllLightsCommand(){
        (new TurnOnAllLightsCommand(smartHome)).execute();
        smartHome.execute(new TestUtils.CheckAllLightStates(true));
    }

    @Test
    void testTurnOnAllLightsInHallCommand(){
        (new TurnOnLightsInHall(smartHome)).execute();
        smartHome.execute(new TestUtils.CheckAllLightStatesInRoom(true, "hall"));
    }
}