package at.fhhagenberg.sqe.panes;

import at.fhhagenberg.elevatorsys.models.CommittedDirection;
import at.fhhagenberg.elevatorsys.models.DoorStatus;
import at.fhhagenberg.elevatorsys.models.ElevatorModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ElevatorPane extends VBox {

    private final ElevatorFloorPane elevatorFloorPane;
    private final Label payloadLabel;
    private final Label speedLabel;

    public ElevatorPane(int numberOfFloors) {
        assert numberOfFloors > 0;
        elevatorFloorPane = new ElevatorFloorPane(numberOfFloors);
        Button manualButton = new Button("  MANUAL  ");
        Button autoButton = new Button("AUTOMATIC");
        payloadLabel = new Label("payload: ");
        speedLabel = new Label("speed: ");
        this.getChildren().addAll(elevatorFloorPane, manualButton,autoButton, payloadLabel, speedLabel);
    }

    public void update(ElevatorModel elevatorModel){
        payloadLabel.setText("payload: " + elevatorModel.getCurrentWeight());
        speedLabel.setText("speed: " + elevatorModel.getCurrentSpeed());
        elevatorFloorPane.setElevatorDirection(elevatorModel.getDirectionStatus());
        elevatorFloorPane.setElevatorDoorStatus(elevatorModel.getDoorStatus());

        unsetAllFloors();
        setFloor(elevatorModel.getCurrentFloor());
        unsetAllLights();
        for(Integer floorNumber : elevatorModel.getSelectedFloors()){
            setLightSelected(floorNumber);
        }
        setLightTarget(elevatorModel.getCurrentFloorTarget());
    }

    public void setElevatorDirection(CommittedDirection direction){
        elevatorFloorPane.setElevatorDirection(direction);
    }

    public void setElevatorDoorStatus(DoorStatus doorStatus){
        elevatorFloorPane.setElevatorDoorStatus(doorStatus);
    }

    public void setLightSelected(int floor) {
        elevatorFloorPane.setFloorLight(floor, Color.YELLOW);
    }

    public void setLightTarget(int floor) {
        elevatorFloorPane.setFloorLight(floor, Color.GREEN);
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

    public void unsetAllLights() { elevatorFloorPane.unsetAllFloorLight();}

    public void unsetAllFloors() {
        elevatorFloorPane.unsetAllFloors();
    }

}
