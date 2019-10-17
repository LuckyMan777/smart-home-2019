package ru.sbt.mipt.oop;

public class Action {
    private final SensorEventType command;
    private final String objectId;

    public Action(SensorEventType command, String objectId) {
        this.command = command;
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public SensorEventType getCommand() {
        return command;
    }
}
