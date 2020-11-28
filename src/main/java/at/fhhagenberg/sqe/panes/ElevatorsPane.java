package at.fhhagenberg.sqe.panes;

import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ElevatorsPane extends HBox {

    ElevatorPane[] elevators;
    private final int numberOfElevators;
    private final int numberOfFloors;

    public ElevatorsPane(int numberOfElevators, int numberOfFloors) {
        this.numberOfElevators = numberOfElevators;
        this.numberOfFloors = numberOfFloors;
        //this.setOrientation(Orientation.VERTICAL);
        elevators = new ElevatorPane[numberOfElevators];

        for (int i = 0; i < numberOfElevators; i++) {
            elevators[i] = new ElevatorPane(numberOfFloors);
        }
        this.getChildren().addAll(elevators);
    }

    public void setFloorForElevator(int elevator, int floor) {
        elevators[elevator].setFloor(floor);
    }

    public void unsetFloorForElevator(int elevator, int floor) {
        elevators[elevator].unsetFloor(floor);
    }

    public void setFloorLightForElevator(int elevator, int floor) {
        elevators[elevator].setLight(floor);
    }

    public void unsetFloorLightForElevator(int elevator, int floor) {
        elevators[elevator].unsetLight(floor);
    }

    public void unsetAllFloorLights() {
        for (ElevatorPane elevatorPane : elevators) {
            elevatorPane.unsetAllLights();
        }
    }

}
