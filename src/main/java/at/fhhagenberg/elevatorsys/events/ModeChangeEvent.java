package at.fhhagenberg.elevatorsys.events;

import at.fhhagenberg.elevatorsys.models.Mode;
import javafx.event.Event;
import javafx.event.EventType;

import java.util.EventObject;

public class ModeChangeEvent extends Event {
    public static final EventType<ModeChangeEvent> MODE_CHANGE_EVENT = new EventType(ANY);

    private int elevatorNumber;
    private Mode mode;

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
