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
