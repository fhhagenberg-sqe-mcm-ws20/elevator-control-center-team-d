package at.fhhagenberg.elevatorsys;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ControlCenter {

    private BuildingModel buildingModel;
    private IElevator elevatorApi;

    public ControlCenter(IElevator elevatorApi) {
        this.elevatorApi = elevatorApi;
        try {
            buildingModel = initialiseBuildingModel();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private BuildingModel initialiseBuildingModel() throws RemoteException {
        final int elevatorNum = this.elevatorApi.getElevatorNum();
        final int floorNum = this.elevatorApi.getFloorNum();
        List<ElevatorModel> elevatorModels = new ArrayList<>();
        List<FloorModel> floorModels = new ArrayList<>();

        for (int i = 0; i < elevatorNum; i++) {
            ElevatorModel.ElevatorModelBuilder builder = new ElevatorModel.ElevatorModelBuilder();
            builder.setDirectionStatus(elevatorApi.getCommittedDirection(i));
            builder.setDoorStatus(elevatorApi.getElevatorDoorStatus(i));
            builder.setCurrentAcceleration(elevatorApi.getElevatorAccel(i));
            builder.setCapacity(elevatorApi.getElevatorCapacity(i));
            builder.setCurrentFloor(elevatorApi.getElevatorFloor(i));
            builder.setCurrentPosition(elevatorApi.getElevatorPosition(i));
            builder.setCurrentSpeed(elevatorApi.getElevatorSpeed(i));
            builder.setCurrentWeight(elevatorApi.getElevatorWeight(i));
            builder.setCurrentFloorTarget(elevatorApi.getTarget(i));

            for (int j = 0; j <= floorNum; j++) {
                if (elevatorApi.getServicesFloors(i,j)) {
                    builder.addServicedFloor(j);
                }
            }
            elevatorModels.add(builder.build());
        }

        //Add floors to list
        for (int i = 0; i < floorNum; i++) {
            boolean buttonUpState = elevatorApi.getFloorButtonUp(i);
            boolean buttonDownState = elevatorApi.getFloorButtonDown(i);
            int floorHeight = elevatorApi.getFloorHeight();
            floorModels.add(new FloorModel(buttonDownState, buttonUpState, i, floorHeight));
        }
        return new BuildingModel(elevatorModels, floorModels);
    }

    public BuildingModel getBuildingModel() {
        return buildingModel;
    }

    public IElevator getElevatorApi() {
        return elevatorApi;
    }
}
