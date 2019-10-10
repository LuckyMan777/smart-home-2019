package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException, ClassNotFoundException {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(
                new SmartHomeJSONProvider("smart-home-1.js"), new SensorEventRandomProvider());
        smartHomeProcessing.smartHomeProcess();
    }
}
