package at.fhhagenberg.elevatorsys.models;

import java.util.HashMap;
import java.util.Map;

import static sqelevator.IElevator.*;

public enum DoorStatus {
    OPEN(1),
    CLOSED(2),
    OPENING(3),
    CLOSING(4);

    private final int value;
    private static final Map<Integer, DoorStatus> map = new HashMap<>();

    DoorStatus(int value) {
        this.value = value;
    }

    static {
        for (DoorStatus doorStatus : DoorStatus.values()) {
            map.put(doorStatus.value, doorStatus);
        }
    }

    public static DoorStatus valueOf(int doorStatus) {
        return map.get(doorStatus);
    }

    public int getValue() {
        return value;
    }

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
