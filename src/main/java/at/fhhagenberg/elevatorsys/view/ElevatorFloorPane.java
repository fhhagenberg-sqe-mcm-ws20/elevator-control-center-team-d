package at.fhhagenberg.elevatorsys.view;

import at.fhhagenberg.elevatorsys.models.CommittedDirection;
import at.fhhagenberg.elevatorsys.models.DoorStatus;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class ElevatorFloorPane extends VBox {

    FloorPane[] floors;
    private final int numberOfFloors;
    private final ElevatorBoxPane elevator;

    public ElevatorFloorPane(int elevatorNumber, int numberOfFloors, EventHandler<MouseEvent> eventHandler) {
        this.numberOfFloors = numberOfFloors-1;
        this.elevator = new ElevatorBoxPane();
        floors = new FloorPane[numberOfFloors];

        for (int i = 0; i < numberOfFloors; i++) {
            floors[i] = new FloorPane(elevatorNumber, numberOfFloors - i - 1);
            floors[i].setId("floor" + (numberOfFloors - i - 1));
            floors[i].setOnMouseClicked(eventHandler);
        }
        floors[numberOfFloors-1].setCurrentElevatorFloor(elevator);
        this.getChildren().addAll(floors);
    }

    public void setFloor(int level) {
        floors[numberOfFloors-level].setCurrentElevatorFloor(elevator);
    }

    public void unsetFloor(int level) {
        floors[numberOfFloors-level].unsetFloor();
    }

    public void setFloorLight(int level, Color color) {
        floors[numberOfFloors-level].setLight(color);
    }

    public void unsetFloorLight(int level) {
        floors[numberOfFloors-level].unsetLight();
    }

    public void unsetAllFloorLight() {
        for (FloorPane floor : floors) {
            floor.unsetLight();
        }
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void unsetAllFloors() {
        for(FloorPane floor : floors){
            floor.unsetFloor();
        }
    }

    public void setElevatorDirection(CommittedDirection direction) {
        elevator.setDirection(direction);
    }

    public void setElevatorDoorStatus(DoorStatus doorStatus) {
        elevator.setDoorStatus(doorStatus);
    }
}
