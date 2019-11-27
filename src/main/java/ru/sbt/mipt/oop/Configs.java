package ru.sbt.mipt.oop;

public class Configs {

    public static String getFilenameWithSmartHome(){
        return "smart-home-1.js";
    }

    /*public static List<EventProcessor> getEventProcessors() {
        List<EventProcessor> sensorEventProcessors = new ArrayList<>();
        sensorEventProcessors.add(new SignalizationDecorator(new LightEventProcessor()));
        sensorEventProcessors.add(new SignalizationDecorator(new DoorEventProcessor()));
        sensorEventProcessors.add(new SignalizationDecorator(new HallClosingEventProcessor(new SimpleCommandSender())));
        sensorEventProcessors.add(new SignalizationDecorator(new SignalizationDeactivatedEventProcessor()));
        sensorEventProcessors.add(new SignalizationDecorator(new SignalizationActivatedEventProcessor()));

        return sensorEventProcessors;
    }*/
}
