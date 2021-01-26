package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevator;
import sqelevator.IElevatorSystem;

public class AutomaticMode extends ModeBase {
    public AutomaticMode(IElevatorSystem elevatorSystem) {
        super(elevatorSystem);
    }

    @Override
    public void execute(ElevatorModel elevator) {
        System.out.println("I bims 1 AutomaticMode");
    }
}
