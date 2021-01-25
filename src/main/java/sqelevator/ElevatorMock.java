package sqelevator;

import at.fhhagenberg.elevatorsys.models.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ElevatorMock implements IElevatorSystem {

    private int currentTick = 0;
    private boolean changeTicker = false;

    private BuildingModel buildingModel;

    @Override
    public void connect() {
        buildingModel = createDummyModel();
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getDirectionStatus().getValue();
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getCurrentAcceleration();
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getSelectedFloors().contains(floor);
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getDoorStatus().getValue();
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getCurrentFloor();
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return buildingModel.getElevators().size();
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getCurrentPosition();
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getCurrentSpeed();
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getCurrentWeight();
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getCapacity();
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        return buildingModel.getFloor(floor).isButtonDown();
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        return buildingModel.getFloor(floor).isButtonUp();
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        return buildingModel.getFloor(0).getFloorHeight();
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return buildingModel.getFloors().size();
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getServicedFloors().contains(floor);
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        return buildingModel.getElevator(elevatorNumber).getCurrentFloorTarget();
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        buildingModel.getElevator(elevatorNumber)
                .setDirectionStatus(CommittedDirection.valueOf(direction));
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        buildingModel.getElevator(elevatorNumber).shouldServeFloor(floor, service);
    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        buildingModel.getElevator(elevatorNumber).setCurrentFloorTarget(target);
    }

    @Override
    public long getClockTick() throws RemoteException {
        int tick = currentTick;
        if (changeTicker) {
            currentTick++;
        }
        changeTicker = !changeTicker;
        return tick;
    }

    private BuildingModel createDummyModel() {
        FloorModel floor0 = new FloorModel(false, false, 0, 50);
        FloorModel floor1 = new FloorModel(false, false, 1, 50);
        FloorModel floor2 = new FloorModel(false, false, 2, 50);
        FloorModel floor3 = new FloorModel(false, false, 3, 50);
        FloorModel floor4 = new FloorModel(false, false, 4, 50);
        FloorModel floor5 = new FloorModel(false, false, 5, 50);
        FloorModel floor6 = new FloorModel(false, false, 6, 50);
        FloorModel floor7 = new FloorModel(false, false, 7, 50);
        FloorModel floor8 = new FloorModel(false, false, 8, 50);
        FloorModel floor9 = new FloorModel(false, false, 9, 50);
        FloorModel floor10 = new FloorModel(false, false, 10, 50);
        FloorModel floor11 = new FloorModel(false, false, 11, 50);

        List<FloorModel> floors = new ArrayList<>();
        floors.add(floor0);
        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
        floors.add(floor4);
        floors.add(floor5);
        floors.add(floor6);
        floors.add(floor7);
        floors.add(floor8);
        floors.add(floor9);
        floors.add(floor10);
        floors.add(floor11);

        List<Integer> servicedFloors = new ArrayList<>();
        servicedFloors.add(0);
        servicedFloors.add(1);
        servicedFloors.add(2);
        servicedFloors.add(3);
        servicedFloors.add(4);
        servicedFloors.add(5);
        servicedFloors.add(6);
        servicedFloors.add(7);
        servicedFloors.add(8);
        servicedFloors.add(9);
        servicedFloors.add(10);
        servicedFloors.add(11);

        ElevatorModel elevator0 = new ElevatorModel(CommittedDirection.UNCOMMITTED,
                DoorStatus.CLOSED,
                0,
                100,
                0,
                0,
                0,
                0,
                0,
                servicedFloors);

        ElevatorModel elevator1 = new ElevatorModel(CommittedDirection.UNCOMMITTED,
                DoorStatus.CLOSED,
                0,
                100,
                0,
                0,
                0,
                0,
                0,
                servicedFloors);

        ElevatorModel elevator2 = new ElevatorModel(CommittedDirection.UNCOMMITTED,
                DoorStatus.CLOSED,
                0,
                100,
                0,
                0,
                0,
                0,
                0,
                servicedFloors);

        List<ElevatorModel> elevators = new ArrayList<>();
        elevators.add(elevator0);
        elevators.add(elevator1);
        elevators.add(elevator2);

        return new BuildingModel(elevators, floors);
    }
}
