package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeJSONProvider implements SmartHomeProvider {
    private final String filename;

    public SmartHomeJSONProvider(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome provideSmartHome() throws IOException, ClassNotFoundException {
        // считываем состояние дома из файла

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        //SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return parseSmartHomeFromJson(json);
    }

    private SmartHome parseSmartHomeFromJson(String json) throws ClassNotFoundException {

        SmartHome smartHome = new SmartHome();

        addDevicesFromJsonToSmartHome(json, smartHome);
        addAdditionalHandlersFromJsonToSmartHome(json, smartHome);

        return smartHome;
    }

    private void addAdditionalHandlersFromJsonToSmartHome(String json, SmartHome smartHome) throws ClassNotFoundException {
        Gson gson = new Gson();
        JsonArray additionalHandlers = new JsonParser().parse(json).getAsJsonObject().get("additionalSensorEventHandlers").getAsJsonArray();
        for (JsonElement additionalHandler : additionalHandlers) {
            String type = additionalHandler.getAsJsonObject().get("className").getAsString();
            Class<?> clazz = Class.forName(type);
            Object handler = gson.fromJson(additionalHandler.toString(), clazz);
            smartHome.addAdditionalSensorEventHandler((AdditionalSensorEventHandler) handler);
        }
    }

    private void addDevicesFromJsonToSmartHome(String json, SmartHome smartHome) throws ClassNotFoundException {
        Gson gson = new Gson();
        JsonArray devices = new JsonParser().parse(json).getAsJsonObject().get("smartDevices").getAsJsonArray();
        for (JsonElement device : devices) {
            String type = device.getAsJsonObject().get("className").getAsString();
            Class<?> clazz = Class.forName(type);
            Object smartDevice = gson.fromJson(device.toString(), clazz);
            smartHome.addDevice((SmartDevice) smartDevice);
        }
    }

}
