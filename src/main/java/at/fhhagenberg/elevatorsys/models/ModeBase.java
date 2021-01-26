package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevatorSystem;

public abstract class ModeBase {
    IElevatorSystem elevatorSystem;

    public ModeBase(IElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
    }

    public abstract void execute(ElevatorModel elevator);
}
