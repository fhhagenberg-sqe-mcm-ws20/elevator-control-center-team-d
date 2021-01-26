package at.fhhagenberg.elevatorsys.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorModelTest {


    @Test
    public void t_changeAllElevatorModelProperties() {

        List<Integer> servicedFloors = new ArrayList<>();
        servicedFloors.add(0);
        servicedFloors.add(1);
        servicedFloors.add(2);
        servicedFloors.add(3);

        ElevatorModel elevator = new ElevatorModel(0,
                CommittedDirection.UNCOMMITTED,
                DoorStatus.CLOSED,
                0,
                100,
                0,
                0,
                0,
                0,
                0,
                new ArrayList<>());

        elevator.setDirectionStatus(CommittedDirection.DOWN);
        elevator.setCurrentAcceleration(100);
        elevator.setCurrentFloor(2);
        elevator.setCurrentPosition(20);
        elevator.setCurrentSpeed(10);
        elevator.setServicedFloors(servicedFloors);
        elevator.selectFloor(3);
        elevator.unselectFloor(3);
        elevator.shouldServeFloor(0, Boolean.TRUE);

        assertEquals(Boolean.TRUE, elevator.servesFloor(0));

    }




}
