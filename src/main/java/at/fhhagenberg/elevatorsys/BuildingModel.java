package at.fhhagenberg.elevatorsys;

import java.util.List;

public class BuildingModel {

    private List<ElevatorModel> elevators;
    private List<FloorModel> floors;

    public BuildingModel(List<ElevatorModel> elevators,
                         List<FloorModel> floors) {
        this.elevators = elevators;
        this.floors = floors;
    }

    public boolean update(BuildingModel buildingModel){
        List<ElevatorModel> elevatorsNew = buildingModel.getElevators();
        List<FloorModel> floorsNew = buildingModel.getFloors();

        if(elevators.size() != elevatorsNew.size() || floors.size() != floorsNew.size()){
            return false;
        }

        for(int i = 0; i < elevators.size(); i++){
            ElevatorModel elevator = elevators.get(i);
            ElevatorModel elevatorNew = elevatorsNew.get(i);

            elevator.setCapacity(elevatorNew.getCapacity());
            elevator.setCurrentAcceleration(elevatorNew.getCurrentAcceleration());
            elevator.setCurrentFloor(elevatorNew.getCurrentFloor());
            elevator.setCurrentFloorTarget(elevatorNew.getCurrentFloorTarget());
            elevator.setCurrentPosition(elevatorNew.getCurrentPosition());
            elevator.setCurrentSpeed(elevatorNew.getCurrentSpeed());
            elevator.setCurrentWeight(elevatorNew.getCurrentWeight());
            elevator.setDirectionStatus(elevatorNew.getDirectionStatus());
            elevator.setDoorStatus(elevatorNew.getDoorStatus());
            elevator.setSelectedFloors(elevatorNew.getSelectedFloors());
            elevator.setServicedFloors(elevatorNew.getServicedFloors());
        }

        for(int i = 0; i < floors.size(); i++){
            FloorModel floor = floors.get(i);
            FloorModel floorNew = floorsNew.get(i);
            floor.setButtonDown(floorNew.isButtonDown());
            floor.setButtonUp(floorNew.isButtonUp());
            floor.setFloorHeight(floorNew.getFloorHeight());
        }

        return true;
    }

    public ElevatorModel getElevator(int elevatorNumber) {
        return elevators.get(elevatorNumber);
    }

    public FloorModel getFloor(int floorNumber) {
        return floors.get(floorNumber);
    }

    public List<ElevatorModel> getElevators() {
        return elevators;
    }

    public List<FloorModel> getFloors() {
        return floors;
    }
}
