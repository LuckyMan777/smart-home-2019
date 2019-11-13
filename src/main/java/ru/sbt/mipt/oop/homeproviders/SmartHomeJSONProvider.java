package ru.sbt.mipt.oop.homeproviders;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Signalization;
import ru.sbt.mipt.oop.devices.SmartDevice;

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
        String json;
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
        JsonArray actionables = new JsonParser().parse(json).getAsJsonObject().get("actionables").getAsJsonArray();
        for (JsonElement actionable : actionables) {
            if (actionable.getAsJsonObject().has("name") &&
                    actionable.getAsJsonObject().has("smartDevices")) {
                String name = actionable.getAsJsonObject().get("name").getAsString();
                JsonArray devices = actionable.getAsJsonObject().get("smartDevices").getAsJsonArray();
                Room r = new Room(new ArrayList<>(), name);
                for (JsonElement device : devices) {
                    String type = device.getAsJsonObject().get("className").getAsString();
                    Class<?> clazz = Class.forName(type);
                    Object smartDevice = gson.fromJson(device.toString(), clazz);
                    r.addSmartDevice((SmartDevice) smartDevice);
                }
                smartHome.addActionable(r);
            } else if (actionable.getAsJsonObject().has("state")) {
                smartHome.addActionable(new Signalization());
            }
        }
    }

}
