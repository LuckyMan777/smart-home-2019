package ru.sbt.mipt.oop.commandsenders;

import ru.sbt.mipt.oop.SensorCommand;

public interface CommandSender {
    void sendCommand(SensorCommand command);
}
