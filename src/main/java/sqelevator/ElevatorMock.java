package sqelevator;

import at.fhhagenberg.elevatorsys.models.*;

import java.util.ArrayList;
import java.util.List;

public class ElevatorMock implements IElevatorSystem {

    private int currentTick = 0;
    private boolean changeTicker = false;
    private boolean connected = true;

    private BuildingModel buildingModel;

    public ElevatorMock() {
        connect();
    }

    @Override
    public void connect() {
        buildingModel = createDummyModel();
        connected = true;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public int getCommittedDirection(int elevatorNumber){
        return buildingModel.getElevator(elevatorNumber).getDirectionStatus().getValue();
    }

    @Override
    public int getElevatorAccel(int elevatorNumber){
        return buildingModel.getElevator(elevatorNumber).getCurrentAcceleration();
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) {
        return buildingModel.getElevator(elevatorNumber).getSelectedFloors().contains(floor);
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getDoorStatus().getValue();
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getCurrentFloor();
    }

    @Override
    public int getElevatorNum() {
        return buildingModel.getElevators().size();
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getCurrentPosition();
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getCurrentSpeed();
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getCurrentWeight();
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getCapacity();
    }

    @Override
    public boolean getFloorButtonDown(int floor)  {
        return buildingModel.getFloor(floor).isButtonDown();
    }

    @Override
    public boolean getFloorButtonUp(int floor) {
        return buildingModel.getFloor(floor).isButtonUp();
    }

    @Override
    public int getFloorHeight() {
        return buildingModel.getFloor(0).getFloorHeight();
    }

    @Override
    public int getFloorNum() {
        return buildingModel.getFloors().size();
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) {
        return buildingModel.getElevator(elevatorNumber).getServicedFloors().contains(floor);
    }

    @Override
    public int getTarget(int elevatorNumber)  {
        return buildingModel.getElevator(elevatorNumber).getCurrentFloorTarget();
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction)  {
        buildingModel.getElevator(elevatorNumber)
                .setDirectionStatus(CommittedDirection.valueOf(direction));
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service)  {
        buildingModel.getElevator(elevatorNumber).shouldServeFloor(floor, service);
    }

    @Override
    public void setTarget(int elevatorNumber, int target)  {
        buildingModel.getElevator(elevatorNumber).setCurrentFloorTarget(target);
    }

    @Override
    public long getClockTick()  {
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

        ElevatorModel elevator0 = new ElevatorModel(0,
                CommittedDirection.UNCOMMITTED,
                DoorStatus.CLOSED,
                0,
                100,
                0,
                0,
                0,
                0,
                0,
                servicedFloors);

        ElevatorModel elevator1 = new ElevatorModel(1,
                CommittedDirection.UNCOMMITTED,
                DoorStatus.CLOSED,
                0,
                100,
                0,
                0,
                0,
                0,
                0,
                servicedFloors);

        ElevatorModel elevator2 = new ElevatorModel(2,
                CommittedDirection.UNCOMMITTED,
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
