package ru.sbt.mipt.oop;

import com.google.gson.*;

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
    public SmartHome provideSmartHome() throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        //SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        JsonArray rooms = new JsonParser().parse(json).getAsJsonObject().get("rooms").getAsJsonArray();;

        SmartHome smartHome = new SmartHome();

        for (JsonElement room: rooms){
            String roomName = room.getAsJsonObject().get("name").getAsString();
            JsonArray devices = room.getAsJsonObject().get("smartDevices").getAsJsonArray();
            //System.out.println(roomName);
            ArrayList<SmartDevice> smartDevices = new ArrayList<>();
            for (JsonElement device: devices) {
                String type = device.getAsJsonObject().get("className").getAsString().substring(16);
                String id = device.getAsJsonObject().get("id").getAsString();
                if (type.equals("Light")){
                    boolean isOn = device.getAsJsonObject().get("isOn").getAsBoolean();
                    smartDevices.add(new Light(id, isOn));
                }
                if (type.equals("Door")){
                    boolean isOpen = device.getAsJsonObject().get("isOpen").getAsBoolean();
                    smartDevices.add(new Door(id, isOpen));
                }
            }
            smartHome.addRoom(new Room(smartDevices, roomName));
        }
        //System.out.println(rooms.toString());

        return smartHome;
    }
}
