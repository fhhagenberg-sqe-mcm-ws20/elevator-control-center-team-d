package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevator;

public class AutomaticMode extends ModeBase {
    public AutomaticMode(IElevator elevatorApi) {
        super(elevatorApi);
    }

    @Override
    public void execute(ElevatorModel elevator) {
        System.out.println("I bims 1 AutomaticMode");
    }
}
