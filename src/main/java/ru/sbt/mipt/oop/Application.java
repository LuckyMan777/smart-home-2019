package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventproviders.SensorEventRandomProvider;
import ru.sbt.mipt.oop.homeproviders.SmartHomeJSONProvider;
import ru.sbt.mipt.oop.homeproviders.SmartHomeProvider;

import java.io.IOException;

public class Application {

    public static void main(String... args) {
        SmartHomeProvider smartHomeProvider = new SmartHomeJSONProvider("smart-home-1.js");
        SmartHome smartHome = smartHomeProvider.provideSmartHome();
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(smartHome, new SensorEventRandomProvider());
        smartHomeProcessing.smartHomeProcess();
    }
}
