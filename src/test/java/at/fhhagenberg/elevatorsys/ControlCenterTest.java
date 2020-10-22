package at.fhhagenberg.elevatorsys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

class ControlCenterTest {

    IElevator elevator;

    @BeforeEach
    void beforeEach() throws RemoteException {
        //GIVEN
        elevator = mock(IElevator.class);
        Mockito.when(elevator.getElevatorNum()).thenReturn(2);
        Mockito.when(elevator.getFloorNum()).thenReturn(10);

        Mockito.when(elevator.getCommittedDirection(anyInt())).thenReturn(2);
        Mockito.when(elevator.getElevatorDoorStatus(anyInt())).thenReturn(2);
        Mockito.when(elevator.getElevatorAccel(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorFloor(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorPosition(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorSpeed(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorWeight(anyInt())).thenReturn(0);
        Mockito.when(elevator.getTarget(anyInt())).thenReturn(0);
        Mockito.when(elevator.getServicesFloors(anyInt(), anyInt())).thenReturn(true);

        Mockito.when(elevator.getFloorHeight()).thenReturn(10);
        Mockito.when(elevator.getFloorButtonUp(anyInt())).thenReturn(false);
        Mockito.when(elevator.getFloorButtonDown(anyInt())).thenReturn(false);
    }

    @Test
    void t_simpleSetup() {
        //WHEN
        ControlCenter controlCenter = new ControlCenter(elevator);

        //THEN
        assertEquals(2, controlCenter.getBuildingModel().getElevators().size());
        assertEquals(10, controlCenter.getBuildingModel().getFloors().size());
    }

    @Test
    void t_committedDirection() throws RemoteException {
        //WHEN
        Mockito.when(elevator.getCommittedDirection(1)).thenReturn(0);
        ControlCenter controlCenter = new ControlCenter(elevator);

        //THEN
        assertEquals(2, controlCenter.getBuildingModel().getElevator(0).getDirectionStatus());
        assertEquals(0, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
    }

    @Test
    void t_elevatorDoorStatus() throws RemoteException {
        //WHEN
        Mockito.when(elevator.getElevatorDoorStatus(1)).thenReturn(0);
        ControlCenter controlCenter = new ControlCenter(elevator);

        //THEN
        assertEquals(2, controlCenter.getBuildingModel().getElevator(0).getDoorStatus());
        assertEquals(0, controlCenter.getBuildingModel().getElevator(1).getDoorStatus());
    }

}