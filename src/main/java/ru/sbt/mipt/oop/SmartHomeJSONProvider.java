package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeJSONProvider implements SmartHomeProvider {
    private final String filename;

    public SmartHomeJSONProvider(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome provideSmartHome() throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        return gson.fromJson(json, SmartHome.class);
    }
}
