package ru.sbt.mipt.oop.devices.signalizationstates;

import ru.sbt.mipt.oop.devices.Signalization;

public class AlarmedState extends SignalizationState {

    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     */
    public AlarmedState(Signalization signalization) {
        super(signalization);
    }

    @Override
    public void onActivate(String password) {
        signalization.changeState(new ActivatedState(signalization, password));
    }

    @Override
    public void onDeactivate(String password) {
        signalization.changeState(new DeactivatedState(signalization));
    }

    @Override
    public void onAlarm() {
    }
}
