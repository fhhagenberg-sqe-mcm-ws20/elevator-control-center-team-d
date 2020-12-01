package at.fhhagenberg.elevatorsys.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorStatusTest {

    @Test
    void fromInt() {
        assertEquals(DoorStatus.CLOSED, DoorStatus.fromInt(2));
        assertEquals(DoorStatus.OPENING, DoorStatus.fromInt(3));
        assertEquals(DoorStatus.CLOSING, DoorStatus.fromInt(4));
        assertThrows(IllegalArgumentException.class, () -> DoorStatus.fromInt(6));
    }
}