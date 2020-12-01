package at.fhhagenberg.elevatorsys;

import at.fhhagenberg.elevatorsys.models.*;
import at.fhhagenberg.sqe.IElevator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ControlCenter {

    private BuildingModel buildingModel;
    private IElevator elevatorApi;

    private List<PropertyChangeListener> listener = new ArrayList<>();

    public ControlCenter(IElevator elevatorApi) {
        this.elevatorApi = elevatorApi;
        try {
            //TODO: change to updateBuilding? but what if it fails because of a changed tick? stays uninitialized
            buildingModel = queryBuilding();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //To be able to inject a Dummy BuildingModel for testing
    public ControlCenter(BuildingModel buildingModel){
        this.buildingModel = buildingModel;
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }

    private void notifyListeners(String property, BuildingModel oldValue, BuildingModel newValue) {
        for (PropertyChangeListener name : listener) {
            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }

   //In Addition check if the clock tick is the same as from the earlier polling so we can skip the data if its the same since data didnt change.
    public boolean updateBuilding() throws RemoteException {
        long tickStart = this.elevatorApi.getClockTick();
        BuildingModel buildingModelNew = queryBuilding();

        if(tickStart != this.elevatorApi.getClockTick()){
            return false;
        }
        notifyListeners("BuildingModel", this.buildingModel, buildingModelNew);
        buildingModel.update(buildingModelNew);
        return true;
    }

    private BuildingModel queryBuilding() throws RemoteException {
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

    private ElevatorModel queryElevator(int elevatorNumber) throws RemoteException {
        final int floorNum = this.elevatorApi.getFloorNum();

        ElevatorModel.ElevatorModelBuilder builder = new ElevatorModel.ElevatorModelBuilder();

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

    private FloorModel queryFloor(int floorNumber) throws RemoteException {
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

}