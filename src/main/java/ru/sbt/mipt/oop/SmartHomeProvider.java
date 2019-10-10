package ru.sbt.mipt.oop;

import java.io.IOException;

public interface SmartHomeProvider {
    SmartHome provideSmartHome() throws IOException;
}
