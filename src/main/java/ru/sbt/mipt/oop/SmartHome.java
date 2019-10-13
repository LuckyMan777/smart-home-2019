package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    Collection<SmartDevice> smartDevices;
    Collection<AdditionalSensorEventHandler> additionalSensorEventHandlers;

    public SmartHome() {
        smartDevices = new ArrayList<>();
        additionalSensorEventHandlers = new ArrayList<>();
    }

    public SmartHome(Collection<SmartDevice> smartDevices, Collection<AdditionalSensorEventHandler> additionalSensorEventHandlers) {
        this.smartDevices = smartDevices;
        this.additionalSensorEventHandlers = additionalSensorEventHandlers;
    }

    public void addDevice(SmartDevice smartDevice) {
        smartDevices.add(smartDevice);
    }

    public void addAdditionalSensorEventHandler(AdditionalSensorEventHandler additionalSensorEventHandler) {
        additionalSensorEventHandlers.add(additionalSensorEventHandler);
    }

    public Collection<SmartDevice> getSmartDevices() {
        return smartDevices;
    }

    public Collection<AdditionalSensorEventHandler> getAdditionalSensorEventHandlers() {
        return additionalSensorEventHandlers;
    }
}
