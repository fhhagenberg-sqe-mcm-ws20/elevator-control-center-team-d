package at.fhhagenberg.elevatorsys.models;

import static at.fhhagenberg.sqe.IElevator.*;

public enum DoorStatus {
    OPEN,
    CLOSED,
    OPENING,
    CLOSING;

    public static DoorStatus fromInt(int value) {
        switch (value) {
            case ELEVATOR_DOORS_OPEN:
                return OPEN;
            case ELEVATOR_DOORS_CLOSED:
                return CLOSED;
            case ELEVATOR_DOORS_OPENING:
                return OPENING;
            case ELEVATOR_DOORS_CLOSING:
                return CLOSING;
            default:
                throw new IllegalArgumentException();
        }
    }
}
