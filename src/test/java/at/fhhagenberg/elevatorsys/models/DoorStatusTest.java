package at.fhhagenberg.elevatorsys.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorStatusTest {

    @Test
    void fromInt() {
        assertEquals(DoorStatus.CLOSED, DoorStatus.fromInt(2));
        assertThrows(IllegalArgumentException.class, () -> DoorStatus.fromInt(6));
    }
}