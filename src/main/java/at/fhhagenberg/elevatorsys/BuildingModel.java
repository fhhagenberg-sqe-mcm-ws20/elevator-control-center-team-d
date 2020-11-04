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

    public void update(BuildingModel buildingModel){
        setElevators(buildingModel.getElevators());
        setFloors(buildingModel.getFloors());
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

    public void setElevators(List<ElevatorModel> elevators) {
        this.elevators = elevators;
    }

    public void setFloors(List<FloorModel> floors) {
        this.floors = floors;
    }
}
