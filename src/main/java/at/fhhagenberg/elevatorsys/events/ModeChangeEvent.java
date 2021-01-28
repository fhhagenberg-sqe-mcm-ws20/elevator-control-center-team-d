package at.fhhagenberg.elevatorsys.events;

import at.fhhagenberg.elevatorsys.models.Mode;
import javafx.event.Event;
import javafx.event.EventType;

public class ModeChangeEvent extends Event {
    private final int elevatorNumber;
    private final Mode mode;

    public ModeChangeEvent(int elevatorNumber, Mode mode) {
        super(ANY);
        this.elevatorNumber = elevatorNumber;
        this.mode = mode;
    }

    public int getElevatorNumber() {
        return elevatorNumber;
    }

    public Mode getElevatorMode() {
        return mode;
    }
}
