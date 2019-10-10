package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeProcessing smartHomeProcessing = new SmartHomeProcessing(
                new SmartHomeJSONProvider("smart-home-1.js"), new SensorEventRandomProvider());
        smartHomeProcessing.smartHomeProcess();
    }
}
