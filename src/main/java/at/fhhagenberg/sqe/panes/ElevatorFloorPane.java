package at.fhhagenberg.sqe.panes;

import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;

public class ElevatorFloorPane extends FlowPane {

    FloorPane[] floors;
    private final int numberOfFloors;

    public ElevatorFloorPane(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors-1;
        this.setOrientation(Orientation.VERTICAL);
        floors = new FloorPane[numberOfFloors];

        for (int i = 0; i < numberOfFloors; i++) {
            floors[i] = new FloorPane();
            floors[i].setPrefWrapLength(110);
        }
        floors[numberOfFloors-1].setFloor();
        this.getChildren().addAll(floors);
    }

    public void setFloor(int level) {
        assert level <= numberOfFloors;
        floors[numberOfFloors-level].setFloor();
    }

    public void unsetFloor(int level) {
        assert level <= numberOfFloors;
        floors[numberOfFloors-level].unsetFloor();
    }

    public void setFloorLight(int level) {
        assert level <= numberOfFloors;
        floors[numberOfFloors-level].setLight();
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

}
