package at.fhhagenberg.elevatorsys;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ControlCenter {

    private BuildingModel buildingModel;
    private IElevator elevatorApi;

    public ControlCenter(IElevator elevatorApi) {
        this.elevatorApi = elevatorApi;
    }

    private void initialiseBuildingModel() throws RemoteException {
        final int elevatorNum = this.elevatorApi.getElevatorNum();
        final int floorNum = this.elevatorApi.getFloorNum();
        List<ElevatorModel> elevatorModels = new ArrayList<>();
        List<FloorModel> floorModels = new ArrayList<>();

        for (int i = 0; i <= elevatorNum; i++) {
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
        }

        //Add floors to list


    }

}
