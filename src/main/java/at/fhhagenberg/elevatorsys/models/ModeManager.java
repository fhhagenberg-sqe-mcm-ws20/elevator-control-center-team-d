package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevator;
import sqelevator.IElevatorSystem;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ModeManager {
    private Map<Mode, ModeBase> modes = new HashMap<>();

    public ModeManager(IElevatorSystem elevatorSystem){
        modes.put(Mode.MANUAL, new ManualMode(elevatorSystem));
        modes.put(Mode.AUTOMATIC, new AutomaticMode(elevatorSystem));
    }

    public void execute(ElevatorModel elevator) throws RemoteException {
        modes.get(elevator.getMode()).execute(elevator);
    }
}
