package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevator;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ModeManager {
    private Map<Mode, ModeBase> modes = new HashMap<>();

    public ModeManager(IElevator elevatorApi){
        modes.put(Mode.MANUAL, new ManualMode(elevatorApi));
        modes.put(Mode.AUTOMATIC, new AutomaticMode(elevatorApi));
    }

    public void execute(ElevatorModel elevator) throws RemoteException {
        modes.get(elevator.getMode()).execute(elevator);
    }
}