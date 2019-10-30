package ru.sbt.mipt.oop.devices.signalizationstates;

import ru.sbt.mipt.oop.devices.Signalization;

public class ActivatedState extends State {

    String password;

    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     */
    public ActivatedState(Signalization signalization, String password) {
        super(signalization);
        this.password = password;
    }

    @Override
    public void onActivate(String password) {
        this.password = password;
    }

    @Override
    public void onDeactivate(String password) {
        if (password.equals(this.password)) {
            signalization.changeState(new DeactivatedState(signalization));
        } else
            signalization.changeState(new AlarmedState(signalization));
    }

    @Override
    public void onAlarm() {
        signalization.changeState(new AlarmedState(signalization));
    }
}
