package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevator;

import java.rmi.RemoteException;

public abstract class ModeBase {
    IElevator elevatorApi;

    public ModeBase(IElevator elevatorApi) {
        this.elevatorApi = elevatorApi;
    }

    public abstract void execute(ElevatorModel elevator) throws RemoteException;
}
