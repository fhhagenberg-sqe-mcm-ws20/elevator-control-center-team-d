package at.fhhagenberg.elevatorsys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
        Mockito.when(elevator.getElevatorCapacity(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorAccel(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorFloor(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorPosition(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorSpeed(anyInt())).thenReturn(0);
        Mockito.when(elevator.getElevatorWeight(anyInt())).thenReturn(0);
        Mockito.when(elevator.getTarget(anyInt())).thenReturn(0);
        Mockito.when(elevator.getServicesFloors(anyInt(), anyInt())).thenReturn(true);
        Mockito.when(elevator.getClockTick()).thenReturn((long)10);

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
    void t_elevatorDataModel() throws RemoteException {
        //WHEN
        Mockito.when(elevator.getCommittedDirection(1)).thenReturn(1);
        Mockito.when(elevator.getElevatorAccel(1)).thenReturn(1);
        Mockito.when(elevator.getElevatorButton(1, 1)).thenReturn(true);
        Mockito.when(elevator.getElevatorDoorStatus(1)).thenReturn(1);
        Mockito.when(elevator.getElevatorFloor(1)).thenReturn(1);
        Mockito.when(elevator.getElevatorPosition(1)).thenReturn(1);
        Mockito.when(elevator.getElevatorSpeed(1)).thenReturn(1);
        Mockito.when(elevator.getElevatorWeight(1)).thenReturn(1);
        Mockito.when(elevator.getElevatorCapacity(1)).thenReturn(1);
        Mockito.when(elevator.getServicesFloors(1, 1)).thenReturn(false);
        Mockito.when(elevator.getTarget(1)).thenReturn(1);
        Mockito.when(elevator.getCommittedDirection(1)).thenReturn(1);
        ControlCenter controlCenter = new ControlCenter(elevator);

        //THEN
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentAcceleration());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getDoorStatus());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentFloor());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentPosition());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentSpeed());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentWeight());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCapacity());
        assertEquals(false, controlCenter.getBuildingModel().getElevator(1).getServicedFloors().contains(1));
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentFloorTarget());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
    }

    @Test
    void t_floorDataModel() throws RemoteException {
        //WHEN
        Mockito.when(elevator.getFloorHeight()).thenReturn(20);
        Mockito.when(elevator.getFloorButtonDown(1)).thenReturn(true);
        Mockito.when(elevator.getFloorButtonUp(1)).thenReturn(true);
        ControlCenter controlCenter = new ControlCenter(elevator);

        //THEN
        assertEquals(20, controlCenter.getBuildingModel().getFloor(1).getFloorHeight());
        assertEquals(true, controlCenter.getBuildingModel().getFloor(1).isButtonDown());
        assertEquals(true, controlCenter.getBuildingModel().getFloor(1).isButtonUp());
    }

    @Test
    void t_updateTickCorrect() throws RemoteException {
        //WHEN
        ControlCenter controlCenter = new ControlCenter(elevator);
        Mockito.when(elevator.getCommittedDirection(1)).thenReturn(1);

        //THEN
        assertEquals(2, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
        assertEquals(true, controlCenter.updateBuilding());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
    }

    @Test
    void t_updateTickChanged() throws RemoteException {
        //WHEN
        Mockito.when(elevator.getClockTick()).thenAnswer(new Answer() {
            private long ticks = 10;

            public Object answer(InvocationOnMock invocation) {
                return ticks++;
            }
        });
        ControlCenter controlCenter = new ControlCenter(elevator);
        Mockito.when(elevator.getCommittedDirection(1)).thenReturn(1);

        //THEN
        assertEquals(2, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
        assertEquals(false, controlCenter.updateBuilding());
        assertEquals(2, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
    }


}