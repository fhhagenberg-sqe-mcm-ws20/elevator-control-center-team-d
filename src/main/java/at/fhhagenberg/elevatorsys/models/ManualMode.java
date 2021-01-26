package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevatorSystem;

public class ManualMode extends ModeBase {
    public ManualMode(IElevatorSystem elevatorSystem) {
        super(elevatorSystem);
    }

    @Override
    public void execute(ElevatorModel elevator) {
        CommittedDirection direction = CommittedDirection.UNCOMMITTED;
        if(elevator.getCurrentFloor() < elevator.getCurrentFloorTarget()){
            direction = CommittedDirection.UP;
        }
        else if (elevator.getCurrentFloor() > elevator.getCurrentFloorTarget()){
            direction = CommittedDirection.DOWN;
        }
        elevatorSystem.setCommittedDirection(elevator.getElevatorNumber(), direction.getValue());
    }
}
