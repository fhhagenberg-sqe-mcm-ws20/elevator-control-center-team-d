package at.fhhagenberg.elevatorsys.models;


import java.util.HashMap;
import java.util.Map;

public enum CommittedDirection {
    UP(0),
    DOWN(1),
    UNCOMMITTED(2);

    private final int value;
    private static final Map<Integer, CommittedDirection> map = new HashMap<>();

    CommittedDirection(int value) {
        this.value = value;
    }

    static {
        for (CommittedDirection committedDirection : CommittedDirection.values()) {
            map.put(committedDirection.value, committedDirection);
        }
    }

    public static CommittedDirection valueOf(int committedDirection) {
        return map.get(committedDirection);
    }

    public int getValue() {
        return value;
    }
}
