package ru.sbt.mipt.oop.devices.signalizationstates;

import ru.sbt.mipt.oop.devices.Signalization;

public class DeactivatedState extends State {

    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     */
    public DeactivatedState(Signalization signalization) {
        super(signalization);
    }

    @Override
    public void onActivate(String password) {
        signalization.changeState(new ActivatedState(signalization, password));
    }

    @Override
    public void onDeactivate(String password) {
    }

    @Override
    public void onAlarm() {
        signalization.changeState(new AlarmedState(signalization));
    }
}
