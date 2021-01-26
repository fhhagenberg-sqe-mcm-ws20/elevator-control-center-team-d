package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevator;
import sqelevator.IElevatorSystem;

import java.rmi.RemoteException;

public class ManualMode extends ModeBase {
    public ManualMode(IElevatorSystem elevatorSystem) {
        super(elevatorSystem);
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

        elevatorSystem.setCommittedDirection(elevator.getElevatorNumber(), direction.getValue());
    }
}
