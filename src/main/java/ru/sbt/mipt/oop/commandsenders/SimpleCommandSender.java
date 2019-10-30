package ru.sbt.mipt.oop.commandsenders;

import ru.sbt.mipt.oop.SensorCommand;

public class SimpleCommandSender implements CommandSender {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
