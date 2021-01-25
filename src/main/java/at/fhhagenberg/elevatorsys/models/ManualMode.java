package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevator;

import java.rmi.RemoteException;

public class ManualMode extends ModeBase {
    public ManualMode(IElevator elevatorApi) {
        super(elevatorApi);
    }

    @Override
    public void execute(ElevatorModel elevator) throws RemoteException {
        CommittedDirection direction = CommittedDirection.UNCOMMITTED;
        if(elevator.getCurrentFloor() < elevator.getCurrentFloorTarget()){
            direction = CommittedDirection.UP;
        }
        else if (elevator.getCurrentFloorTarget() > elevator.getCurrentFloorTarget()){
            direction = CommittedDirection.DOWN;
        }

        elevatorApi.setCommittedDirection(elevator.getElevatorNumber(), direction.getValue());
    }
}
