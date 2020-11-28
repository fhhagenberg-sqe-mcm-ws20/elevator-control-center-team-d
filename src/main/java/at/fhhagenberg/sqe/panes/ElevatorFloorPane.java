package at.fhhagenberg.sqe.panes;

import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ElevatorFloorPane extends VBox {

    FloorPane[] floors;
    private final int numberOfFloors;
    private final ElevatorBoxPane elevator;

    public ElevatorFloorPane(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors-1;
        this.elevator = new ElevatorBoxPane();
        floors = new FloorPane[numberOfFloors];

        for (int i = 0; i < numberOfFloors; i++) {
            floors[i] = new FloorPane();
        }
        floors[numberOfFloors-1].setFloor(elevator);
        this.getChildren().addAll(floors);
    }

    public void setFloor(int level) {
        assert level <= numberOfFloors;
        floors[numberOfFloors-level].setFloor(elevator);
    }

    public void unsetFloor(int level) {
        assert level <= numberOfFloors;
        floors[numberOfFloors-level].unsetFloor();
    }

    public void setFloorLight(int level, Color color) {
        assert level <= numberOfFloors;
        floors[numberOfFloors-level].setLight(color);
    }

    public void unsetFloorLight(int level) {
        assert level <= numberOfFloors;
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
}
