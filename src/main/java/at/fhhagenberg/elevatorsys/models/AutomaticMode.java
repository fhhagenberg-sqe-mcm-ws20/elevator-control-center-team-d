package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevatorSystem;

public class AutomaticMode extends ModeBase {
    public AutomaticMode(IElevatorSystem elevatorSystem) {
        super(elevatorSystem);
    }

    @Override
    public void execute(ElevatorModel elevator) {
        //not yet implemented
    }
}
