package at.fhhagenberg.elevatorsys.view;

import at.fhhagenberg.elevatorsys.events.ModeChangeEvent;
import at.fhhagenberg.elevatorsys.models.CommittedDirection;
import at.fhhagenberg.elevatorsys.models.DoorStatus;
import at.fhhagenberg.elevatorsys.models.ElevatorModel;
import at.fhhagenberg.elevatorsys.models.Mode;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.swing.*;


public class ElevatorPane extends VBox {

    private final ElevatorFloorPane elevatorFloorPane;
    private final Label payloadLabel;
    private final Label speedLabel;
    
    private int elevatorNumber;

    public ElevatorPane(int elevatorNumber, int numberOfFloors, EventHandler eventHandler) {
        assert numberOfFloors > 0;
        this.elevatorNumber = elevatorNumber;
        elevatorFloorPane = new ElevatorFloorPane(elevatorNumber, numberOfFloors, eventHandler);

        RadioButton manualButton = new RadioButton("MANUAL");
        RadioButton autoButton = new RadioButton("AUTOMATIC");
        ToggleGroup modeButtons = new ToggleGroup();
        manualButton.setToggleGroup(modeButtons);
        autoButton.setToggleGroup(modeButtons);
        modeButtons.selectToggle(manualButton);
        modeButtons.selectedToggleProperty().addListener((observable, oldVal, newVal) -> eventHandler.handle(new ModeChangeEvent(elevatorNumber, Mode.valueOf(((RadioButton)newVal).getText()))));

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
        elevatorFloorPane.setFloorLight(floor, Color.ORANGE);
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

    public int getElevatorNumber() {
        return elevatorNumber;
    }

}
