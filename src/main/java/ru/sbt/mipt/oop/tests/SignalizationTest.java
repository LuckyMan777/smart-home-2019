package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.devices.Signalization;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SignalizationTest {
    Signalization signalization;

    @BeforeEach
    void setUp() {
        signalization = new Signalization();
    }

    @Test
    void activate() {
        signalization.activate("333");
        assertTrue(signalization.isActivated());
    }

    @Test
    void deactivate() {
        signalization.activate("333");
        signalization.deactivate("333");
        assertTrue(signalization.isDeactivated());

        signalization.activate("333");
        signalization.deactivate("111");
        assertTrue(signalization.isAlarmed());
    }

    @Test
    void toAlarm() {
        signalization.toAlarm();
        assertTrue(signalization.isAlarmed());
    }
}