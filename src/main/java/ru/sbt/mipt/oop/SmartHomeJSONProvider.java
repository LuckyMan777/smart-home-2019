package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SmartHomeJSONProvider implements SmartHomeProvider {
    private final String filename;

    public SmartHomeJSONProvider(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome provideSmartHome() {
        // считываем состояние дома из файла
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
            return parseSmartHomeFromJson(json);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SmartHome parseSmartHomeFromJson(String json) throws ClassNotFoundException {

        SmartHome smartHome = new SmartHome();

        addRoomsFromJsonToSmartHome(json, smartHome);

        return smartHome;
    }

    private void addRoomsFromJsonToSmartHome(String json, SmartHome smartHome) throws ClassNotFoundException {
        Gson gson = new Gson();
        JsonArray rooms = new JsonParser().parse(json).getAsJsonObject().get("rooms").getAsJsonArray();
        for (JsonElement room : rooms) {
            String name = room.getAsJsonObject().get("name").getAsString();
            JsonArray devices = room.getAsJsonObject().get("smartDevices").getAsJsonArray();
            Room r = new Room(new ArrayList<>(), name);
            for (JsonElement device : devices) {
                String type = device.getAsJsonObject().get("className").getAsString();
                Class<?> clazz = Class.forName(type);
                Object smartDevice = gson.fromJson(device.toString(), clazz);
                r.addSmartDevice((SmartDevice) smartDevice);
            }
            smartHome.addRoom(r);
        }
    }

}
