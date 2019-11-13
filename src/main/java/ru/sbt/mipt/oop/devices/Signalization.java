package ru.sbt.mipt.oop.devices;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.devices.signalizationstates.ActivatedState;
import ru.sbt.mipt.oop.devices.signalizationstates.AlarmedState;
import ru.sbt.mipt.oop.devices.signalizationstates.DeactivatedState;
import ru.sbt.mipt.oop.devices.signalizationstates.SignalizationState;

public class Signalization implements Actionable {
    private SignalizationState state;

    public Signalization() {
        this.state = new DeactivatedState(this);
    }

    public void changeState(SignalizationState state) {
        this.state = state;
    }

    public void activate(String password) {
        state.onActivate(password);
    }

    public void deactivate(String password) {
        state.onDeactivate(password);
    }

    public void toAlarm() {
        state.onAlarm();
    }

    public boolean isActivated() {
        return state instanceof ActivatedState;
    }

    public boolean isDeactivated() {
        return state instanceof DeactivatedState;
    }

    public boolean isAlarmed() {
        return state instanceof AlarmedState;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
