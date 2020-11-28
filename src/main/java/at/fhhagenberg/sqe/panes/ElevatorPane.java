package at.fhhagenberg.sqe.panes;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ElevatorPane extends FlowPane {

    private final ElevatorFloorPane elevatorFloorPane;
    private final int maxHeight;

    public ElevatorPane(int numberOfFloors) {
        assert numberOfFloors > 0;
        this.setOrientation(Orientation.VERTICAL);
        elevatorFloorPane = new ElevatorFloorPane(numberOfFloors);
        Button manualButton = new Button("  MANUAL  ");
        Button autoButton = new Button("AUTOMATIC");
        this.getChildren().addAll(elevatorFloorPane, manualButton,autoButton);
        maxHeight = (elevatorFloorPane.getNumberOfFloors()+1)*50+10;
        elevatorFloorPane.setMaxHeight(maxHeight);
    }

    public int getMyMaxHeight() {
        return maxHeight;
    }

    public void setLight(int floor) {
        elevatorFloorPane.setFloorLight(floor);
    }

    public void setFloor(int floor) {
        elevatorFloorPane.setFloor(floor);
    }

    public void unsetLight(int floor) {
        elevatorFloorPane.unsetFloorLight(floor);
    }

    public void unsetFloor(int floor) {
        elevatorFloorPane.unsetFloor(floor);
    }

    public void unsetAllLights() {
        elevatorFloorPane.unsetAllFloorLight();
    }

}
