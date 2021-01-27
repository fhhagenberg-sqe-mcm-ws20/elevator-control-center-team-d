package at.fhhagenberg.elevatorsys.events;

import at.fhhagenberg.elevatorsys.models.Mode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModeChangeEventTest {

    @Test
    void t_testModeChanges() {
        ModeChangeEvent modeChangeEvent = new ModeChangeEvent(0, Mode.MANUAL);
        ModeChangeEvent modeChangeEvent1 = new ModeChangeEvent(1, Mode.AUTOMATIC);

        assertEquals(0, modeChangeEvent.getElevatorNumber());
        assertEquals(Mode.MANUAL, modeChangeEvent.getElevatorMode());

        assertEquals(1, modeChangeEvent1.getElevatorNumber());
        assertEquals(Mode.AUTOMATIC, modeChangeEvent1.getElevatorMode());
    }



}
