package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {
        SmartHome smartHome = getSmartHome();
        writeSmartHomeToJSON(smartHome);
    }

    private static SmartHome getSmartHome() {
        List<SmartDevice> smartDevices = new ArrayList<>();
        smartDevices.add(new Light("1", "kitchen", false));
        smartDevices.add(new Light("2", "kitchen", true));
        smartDevices.add(new Door("1", "kitchen", false));

        smartDevices.add(new Light("3", "bathroom", true));
        smartDevices.add(new Door("2", "bathroom", false));

        smartDevices.add(new Light("4", "bedroom", false));
        smartDevices.add(new Light("5", "bedroom", false));
        smartDevices.add(new Light("6", "bedroom", false));
        smartDevices.add(new Door("3", "bedroom", true));

        smartDevices.add(new Light("7", "hall", false));
        smartDevices.add(new Light("8", "hall", false));
        smartDevices.add(new Light("9", "hall", false));
        smartDevices.add(new Door("4", "hall", false));

        List<AdditionalSensorEventHandler> additionalSensorEventHandlers = new ArrayList<>();
        additionalSensorEventHandlers.add(new HallClosingEventHandler());

/*
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true),
                new Door("1", false)),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true), new Door("2", false)),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false),
                new Light("6", false), new Door("3", true)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false),
                new Light("9", false), new Door("4", false)),
                "hall");
*/

        return new SmartHome(smartDevices, additionalSensorEventHandlers);
    }


    private static void writeSmartHomeToJSON(SmartHome smartHome) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get("output.js");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}
