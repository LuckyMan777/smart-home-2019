package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.Signalization;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {
        SmartHome smartHome = getSmartHome();
        writeSmartHomeToJSON(smartHome);
    }

    private static SmartHome getSmartHome() {
        Room kitchen = new Room(Arrays.asList(new Light("1", "kitchen", false),
                new Light("2", "kitchen", true),
                new Door("1", "kitchen", false)),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", "bathroom", true),
                new Door("2", "bathroom", false)),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", "bedroom", false),
                new Light("5", "bedroom", false),
                new Light("6", "bedroom", false),
                new Door("3", "bedroom", true)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", "hall", false),
                new Light("8", "hall", false),
                new Light("9", "hall", false),
                new Door("4", "hall", false)),
                "hall");

        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall, new Signalization()));
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
