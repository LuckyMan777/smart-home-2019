package ru.sbt.mipt.oop.devices.signalizationstates;

import ru.sbt.mipt.oop.devices.Signalization;

public abstract class SignalizationState {
    transient Signalization signalization;

    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     */
    SignalizationState(Signalization signalization) {
        this.signalization = signalization;
    }

    public abstract void onActivate(String password);

    public abstract void onDeactivate(String password);

    public abstract void onAlarm();
}