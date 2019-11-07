package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.commands.Command;
import ru.sbt.mipt.oop.remotecontrol.commands.SmartHomeCommand;

import java.util.*;

public class SmartHomeRemoteControl implements rc.RemoteControl {

    private Set<String> buttonCodes = new HashSet<>(Arrays.asList("1", "2", "3", "4", "A", "B", "C", "D"));
    private Map<String, Command> map = new HashMap<>();

    private boolean checkButtonCode(String buttonCode) {
        return buttonCodes.contains(buttonCode);
    }

    public void registerButtonToCommand(String buttonCode, Command command) {
        if (checkButtonCode(buttonCode)) {
            map.put(buttonCode, command);
        }
    }

    public Command getCommand(String buttonCode){
        if (!checkButtonCode(buttonCode)){
            return null;
        }
        return map.get(buttonCode);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (map.containsKey(buttonCode)) {
            map.get(buttonCode).execute();
        }
    }
}
