package at.fhhagenberg.elevatorsys;

import at.fhhagenberg.elevatorsys.events.ModeChangeEvent;
import at.fhhagenberg.elevatorsys.models.*;
import at.fhhagenberg.elevatorsys.view.FloorPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import sqelevator.IElevatorSystem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ControlCenter implements EventHandler {

    private final BuildingModel buildingModel;
    private final IElevatorSystem elevatorApi;
    private final ModeManager modeManager;

    private final List<PropertyChangeListener> listener = new ArrayList<>();

    public ControlCenter(IElevatorSystem elevatorApi) {
        this.elevatorApi = elevatorApi;
        this.modeManager = new ModeManager(elevatorApi);
        buildingModel = queryBuilding();
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }

    private void notifyListeners(BuildingModel oldValue, BuildingModel newValue) {
        for (PropertyChangeListener name : listener) {
            name.propertyChange(new PropertyChangeEvent(this, "BuildingModel", oldValue, newValue));
        }
    }

   //In Addition check if the clock tick is the same as from the earlier polling so we can skip the data if its the same since data didnt change.
    public boolean updateBuilding(){
        long tickStart = this.elevatorApi.getClockTick();
        BuildingModel buildingModelNew = queryBuilding();

        if(tickStart != this.elevatorApi.getClockTick()){
            return false;
        }

        buildingModel.update(buildingModelNew);
        notifyListeners(this.buildingModel, buildingModelNew);
        modeManager.execute(buildingModelNew);
        return true;
    }

    private BuildingModel queryBuilding() {
        final int elevatorNum = this.elevatorApi.getElevatorNum();
        final int floorNum = this.elevatorApi.getFloorNum();
        List<ElevatorModel> elevatorModels = new ArrayList<>();
        List<FloorModel> floorModels = new ArrayList<>();

        for (int i = 0; i < elevatorNum; i++) {
            elevatorModels.add(queryElevator(i));
        }

        //Add floors to list
        for (int i = 0; i < floorNum; i++) {
            floorModels.add(queryFloor(i));
        }
        return new BuildingModel(elevatorModels, floorModels);
    }

    private ElevatorModel queryElevator(int elevatorNumber) {
        final int floorNum = this.elevatorApi.getFloorNum();

        ElevatorModel.ElevatorModelBuilder builder = new ElevatorModel.ElevatorModelBuilder();

        builder.setElevatorNumber(elevatorNumber);
        builder.setDirectionStatus(elevatorApi.getCommittedDirection(elevatorNumber));
        builder.setDoorStatus(elevatorApi.getElevatorDoorStatus(elevatorNumber));
        builder.setCurrentAcceleration(elevatorApi.getElevatorAccel(elevatorNumber));
        builder.setCapacity(elevatorApi.getElevatorCapacity(elevatorNumber));
        builder.setCurrentFloor(elevatorApi.getElevatorFloor(elevatorNumber));
        builder.setCurrentPosition(elevatorApi.getElevatorPosition(elevatorNumber));
        builder.setCurrentSpeed(elevatorApi.getElevatorSpeed(elevatorNumber));
        builder.setCurrentWeight(elevatorApi.getElevatorWeight(elevatorNumber));
        builder.setCurrentFloorTarget(elevatorApi.getTarget(elevatorNumber));

        for (int j = 0; j < floorNum; j++) {
            if (elevatorApi.getServicesFloors(elevatorNumber,j)) {
                builder.addServicedFloor(j);
            }
        }

        return builder.build();
    }

    private FloorModel queryFloor(int floorNumber) {
        boolean buttonUpState = elevatorApi.getFloorButtonUp(floorNumber);
        boolean buttonDownState = elevatorApi.getFloorButtonDown(floorNumber);
        int floorHeight = elevatorApi.getFloorHeight();

        return new FloorModel(buttonDownState, buttonUpState, floorNumber, floorHeight);
    }

    public BuildingModel getBuildingModel() {
        return buildingModel;
    }

    public int getNumberOfFloors() {
        return buildingModel.getFloors().size();
    }

    public int getNumberOfElevators() {
        return buildingModel.getElevators().size();
    }

    public void setControlMode(int elevatorNumber, Mode mode) {
        modeManager.setModeForElevator(mode, elevatorNumber);
    }

    public ModeManager getModeManager() {
        return modeManager;
    }

    private void setElevatorTarget(int elevatorNumber, int targetFloor) {
        if (modeManager.getModeForElevator(elevatorNumber) == Mode.MANUAL) {
            elevatorApi.setTarget(elevatorNumber, targetFloor);
        }
    }

    public boolean getConnectionStatus(){
         return elevatorApi.isConnected();
    }

    @Override
    public void handle(Event event) {
        if(event instanceof MouseEvent){
            FloorPane floorPane = (FloorPane) event.getSource();
            setElevatorTarget(floorPane.getElevatorNumber(), floorPane.getFloorNumber());

        }
        else if(event instanceof  ModeChangeEvent){
            ModeChangeEvent modeChangeEvent = (ModeChangeEvent)event;
            setControlMode(modeChangeEvent.getElevatorNumber(), modeChangeEvent.getElevatorMode());
        }
    }
}