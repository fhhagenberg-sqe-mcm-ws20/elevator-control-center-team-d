package at.fhhagenberg.elevatorsys;

import at.fhhagenberg.elevatorsys.models.*;
import at.fhhagenberg.sqe.IElevator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static at.fhhagenberg.sqe.IElevator.*;

public class ControlCenter {

    private BuildingModel buildingModel;
    private IElevator elevatorApi;

    private PropertyChangeSupport support;

    public ControlCenter(IElevator elevatorApi) {
        support = new PropertyChangeSupport(this);
        this.elevatorApi = elevatorApi;
        try {
            //TODO: change to updateBuilding? but what if it fails because of a changed tick? stays uninitialized
            buildingModel = queryBuilding();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangedListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

   //In Addition check if the clock tick is the same as from the earlier polling so we can skip the data if its the same since data didnt change.
    public boolean updateBuilding() throws RemoteException {
        long tickStart = this.elevatorApi.getClockTick();
        BuildingModel buildingModelNew = queryBuilding();

        if(tickStart != this.elevatorApi.getClockTick()){
            return false;
        }
        support.firePropertyChange("buildingModel", this.buildingModel, buildingModelNew);
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

        for (int j = 0; j <= floorNum; j++) {
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

        return new FloorModel(buttonDownState, buttonUpState, floorHeight, floorHeight);
    }

    public BuildingModel getBuildingModel() {
        return buildingModel;
    }

    public IElevator getElevatorApi() {
        return elevatorApi;
    }



    public int getNumberOfFloors() {
        return buildingModel.getFloors().size();
    }

    public int getNumberOfElevators() {
        return buildingModel.getElevators().size();
    }

    public boolean automaticControl(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).isAutomaticControlActivated();
    }

    /**
     * @return floor number of the current elevator position.
     */
    public int getElevatorPosition(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getCurrentFloor();
    }

    /**
     * @return enum value for the current door status of the chosen elevator
     */
    public DoorStatus getDoorStatus(int elevatorNumber) {
        int status = buildingModel.getElevator(elevatorNumber).getDoorStatus();
        DoorStatus doorStatus;
        switch (status) {
            case ELEVATOR_DOORS_OPEN:
                doorStatus = DoorStatus.OPEN;
                break;
            case ELEVATOR_DOORS_CLOSED:
                doorStatus = DoorStatus.CLOSED;
                break;
            case ELEVATOR_DOORS_OPENING:
                doorStatus = DoorStatus.OPENING;
                break;
            case ELEVATOR_DOORS_CLOSING:
                doorStatus = DoorStatus.CLOSING;
                break;
            default:
                throw new IllegalStateException("Unexpected door status: " + status);
        }
        return doorStatus;
    }

    /**
     * @return floor number of the current target
     */
    public int getTarget(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getCurrentFloorTarget();
    }

    /**
     * @return enum value of the committed direction of the chosen elevator
     */
    public CommittedDirection getCommittedDirection(int elevatorNumber) {
        int direction = buildingModel.getElevator(elevatorNumber).getDirectionStatus();
        CommittedDirection committedDirection;
        switch (direction) {
            case ELEVATOR_DIRECTION_UP:
                committedDirection = CommittedDirection.UP;
                break;
            case ELEVATOR_DIRECTION_DOWN:
                committedDirection = CommittedDirection.DOWN;
                break;
            case ELEVATOR_DIRECTION_UNCOMMITTED:
                committedDirection = CommittedDirection.UNCOMMITTED;
                break;
            default:
                throw new IllegalStateException("Unexpected direction value: " + direction);
        }
        return committedDirection;
    }

    /**
     * @return array of floor numbers which are selected in a certain elevator
     */
    public List<Integer> getSelectedFloors(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getSelectedFloors();
    }

    /**
     * @return state of of floor button up/down in certain floor(used enum with up, down, uncommitted).
     */
    public CommittedDirection getFloorButtonDirection(int floorNumber) {
        CommittedDirection selectedDirection;
        if (buildingModel.getFloor(floorNumber).isButtonUp()) {
            selectedDirection = CommittedDirection.UP;
        } else if (buildingModel.getFloor(floorNumber).isButtonDown()) {
            selectedDirection = CommittedDirection.DOWN;
        } else {
            selectedDirection = CommittedDirection.UNCOMMITTED;
        }
        return selectedDirection;
    }

    /**
     * @return array of floor numbers which are not serviced by a certain elevator
     */
    public List<Integer> getServicedFloors(int elevatorNumber) {
        return buildingModel.getElevator(elevatorNumber).getServicedFloors()  ;
    }
}