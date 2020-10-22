package at.fhhagenberg.elevatorsys;

import java.util.ArrayList;
import java.util.List;

public class ElevatorModel {

    private int directionStatus;
    private int doorStatus;
    private int currentAcceleration;
    private int capacity;
    private int currentFloor;
    private int currentPosition;
    private int currentSpeed;
    private int currentWeight;
    private int currentFloorTarget;
    private List<Integer> servicedFloors;
    private List<Integer> selectedFloors;

    public ElevatorModel(int directionStatus, int doorStatus, int currentAcceleration, int capacity, int currentFloor,
                         int currentPosition, int currentSpeed, int currentWeight,
                         int currentFloorTarget, List<Integer> servicedFloors) {
        this.directionStatus = directionStatus;
        this.doorStatus = doorStatus;
        this.currentAcceleration = currentAcceleration;
        this.capacity = capacity;
        this.currentFloor = currentFloor;
        this.currentPosition = currentPosition;
        this.currentSpeed = currentSpeed;
        this.currentWeight = currentWeight;
        this.currentFloorTarget = currentFloorTarget;
        this.servicedFloors = servicedFloors;
        this.selectedFloors = new ArrayList<>();
    }

    public int getDirectionStatus() {
        return directionStatus;
    }

    public void setDirectionStatus(int directionStatus) {
        this.directionStatus = directionStatus;
    }

    public int getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(int doorStatus) {
        this.doorStatus = doorStatus;
    }

    public int getCurrentAcceleration() {
        return currentAcceleration;
    }

    public void setCurrentAcceleration(int currentAcceleration) {
        this.currentAcceleration = currentAcceleration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public List<Integer> getServicedFloors() {
        return servicedFloors;
    }

    public void setServicedFloors(List<Integer> servicedFloors) {
        this.servicedFloors = servicedFloors;
    }

    public int getCurrentFloorTarget() {
        return currentFloorTarget;
    }

    public void setCurrentFloorTarget(int currentFloorTarget) {
        this.currentFloorTarget = currentFloorTarget;
    }

    public boolean servesFloor(int floor) {
        return servicedFloors.contains(floor);
    }

    public List<Integer> getSelectedFloors() {
        return selectedFloors;
    }

    public void setSelectedFloors(List<Integer> selectedFloors) {
        this.selectedFloors = selectedFloors;
    }

    public void selectFloor(Integer floor) {
        if (!selectedFloors.contains(floor)) {
            selectedFloors.add(floor);
        }
    }

    public void unselectFloor(Integer floor) {
        selectedFloors.remove(floor);
    }

    public void shouldServeFloor(int floor, boolean serve) {
        servicedFloors.remove(floor);
        if (serve) {
            servicedFloors.add(floor);
        }
    }


    public static class ElevatorModelBuilder{

        private int directionStatus;
        private int doorStatus;
        private int currentAcceleration;
        private int capacity;
        private int currentFloor;
        private int currentPosition;
        private int currentSpeed;
        private int currentWeight;
        private int currentFloorTarget;
        private List<Integer> servicedFloors = new ArrayList<>();

        public void setDirectionStatus(int directionStatus) {
            this.directionStatus = directionStatus;
        }

        public void setDoorStatus(int doorStatus) {
            this.doorStatus = doorStatus;
        }

        public void setCurrentAcceleration(int currentAcceleration) {
            this.currentAcceleration = currentAcceleration;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public void setCurrentFloor(int currentFloor) {
            this.currentFloor = currentFloor;
        }

        public void setCurrentPosition(int currentPosition) {
            this.currentPosition = currentPosition;
        }

        public void setCurrentSpeed(int currentSpeed) {
            this.currentSpeed = currentSpeed;
        }

        public void setCurrentWeight(int currentWeight) {
            this.currentWeight = currentWeight;
        }

        public void setCurrentFloorTarget(int currentFloorTarget) {
            this.currentFloorTarget = currentFloorTarget;
        }

        public void addServicedFloor(int floor) {
            servicedFloors.add(floor);
        }

        public void setServicedFloors(List<Integer> servicedFloors) {
            this.servicedFloors = servicedFloors;
        }

        public ElevatorModel build() {
            return new ElevatorModel(directionStatus, doorStatus, currentAcceleration, capacity, currentFloor,
                    currentPosition, currentSpeed, currentWeight, currentFloorTarget, servicedFloors);
        }
    }

}
