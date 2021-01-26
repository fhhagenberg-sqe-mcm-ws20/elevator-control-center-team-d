package at.fhhagenberg.elevatorsys.controlcenter;

import at.fhhagenberg.elevatorsys.ControlCenter;
import at.fhhagenberg.elevatorsys.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import sqelevator.IElevatorSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

class ControlCenterTest {

    IElevatorSystem elevator;

    @BeforeEach
    void beforeEach(){
        //GIVEN
        elevator = mock(IElevatorSystem.class);
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
        Mockito.when(elevator.getClockTick()).thenReturn(10L);

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
    void t_elevatorDataModel() {
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
        assertEquals(CommittedDirection.DOWN, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentAcceleration());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentPosition());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentSpeed());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCurrentWeight());
        assertEquals(1, controlCenter.getBuildingModel().getElevator(1).getCapacity());
    }

    @Test
    void t_elevatorDataModelSetIllegalValuesThrows(){
        //WHEN
        ControlCenter controlCenter = new ControlCenter(elevator);

        assertThrows(IllegalArgumentException.class, () -> controlCenter.getBuildingModel().getElevator(1).setCapacity(-10));
        assertThrows(IllegalArgumentException.class, () -> controlCenter.getBuildingModel().getElevator(1).setCurrentWeight(-10));
    }

    @Test
    void t_floorDataModel() {
        //WHEN
        Mockito.when(elevator.getFloorHeight()).thenReturn(20);
        Mockito.when(elevator.getFloorButtonDown(1)).thenReturn(false);
        Mockito.when(elevator.getFloorButtonUp(1)).thenReturn(false);
        ControlCenter controlCenter = new ControlCenter(elevator);
        FloorModel floor = controlCenter.getBuildingModel().getFloor(1);
        floor.setButtonUp(true);
        floor.setButtonDown(true);

        //THEN
        assertEquals(20, controlCenter.getBuildingModel().getFloor(1).getFloorHeight());
        assertEquals(1, controlCenter.getBuildingModel().getFloor(1).getFloorNr());
    }

    @Test
    void t_buildingDataModel(){
        //WHEN
        ControlCenter controlCenter = new ControlCenter(elevator);

        //THEN
        assertEquals(2, controlCenter.getNumberOfElevators());
        assertEquals(10, controlCenter.getNumberOfFloors());
    }

    @Test
    void t_updateTickCorrect() {
        //WHEN
        ControlCenter controlCenter = new ControlCenter(elevator);
        Mockito.when(elevator.getCommittedDirection(1)).thenReturn(1);

        //THEN
        assertEquals(CommittedDirection.UNCOMMITTED, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
        assertEquals(true, controlCenter.updateBuilding());
        assertEquals(CommittedDirection.DOWN, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
    }

    @Test
    void t_updateTickChanged() {
        //WHEN
        Mockito.when(elevator.getClockTick()).thenAnswer(new Answer() {
            private long ticks = 10L;

            public Object answer(InvocationOnMock invocation) {
                return ticks++;
            }
        });
        ControlCenter controlCenter = new ControlCenter(elevator);
        Mockito.when(elevator.getCommittedDirection(1)).thenReturn(1);

        //THEN
        assertEquals(CommittedDirection.UNCOMMITTED, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
        assertEquals(false, controlCenter.updateBuilding());
        assertEquals(CommittedDirection.UNCOMMITTED, controlCenter.getBuildingModel().getElevator(1).getDirectionStatus());
    }

    @Test
    void t_mode() {
        //GIVEN
        ControlCenter controlCenter = new ControlCenter(elevator);

        //WHEN
        controlCenter.setControlMode(0, Mode.MANUAL);
        controlCenter.setControlMode(1, Mode.AUTOMATIC);

        //THEN
        ModeManager modeManager = controlCenter.getModeManager();
        assertEquals(Mode.MANUAL, modeManager.getModeForElevator(0));
        assertEquals(Mode.AUTOMATIC, modeManager.getModeForElevator(1));
    }
}