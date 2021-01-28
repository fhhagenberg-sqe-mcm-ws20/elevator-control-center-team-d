package at.fhhagenberg.elevatorsys.events;

import at.fhhagenberg.elevatorsys.models.Mode;
import javafx.event.Event;
import javafx.event.EventType;

public class ModeChangeEvent extends Event {
    public static final EventType<ModeChangeEvent> MODE_CHANGE_EVENT = new EventType(ANY);

    private final int elevatorNumber;
    private final Mode mode;

    public ModeChangeEvent(int elevatorNumber, Mode mode) {
        super(MODE_CHANGE_EVENT);
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
