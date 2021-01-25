package sqelevator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiElevatorAdapter implements IElevatorSystem {

    private IElevator elevatorApi;

    @Override
    public void connect() {
        try {
            elevatorApi = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            //TODO: throw the wanted exception
        }
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        return elevatorApi.getCommittedDirection(elevatorNumber);
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        return elevatorApi.getElevatorAccel(elevatorNumber);
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        return elevatorApi.getElevatorButton(elevatorNumber, floor);
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        return elevatorApi.getElevatorDoorStatus(elevatorNumber);
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        return elevatorApi.getElevatorFloor(elevatorNumber);
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return elevatorApi.getElevatorNum();
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        return elevatorApi.getElevatorPosition(elevatorNumber);
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        return elevatorApi.getElevatorSpeed(elevatorNumber);
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        return elevatorApi.getElevatorWeight(elevatorNumber);
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        return elevatorApi.getElevatorCapacity(elevatorNumber);
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        return elevatorApi.getFloorButtonDown(floor);
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        return elevatorApi.getFloorButtonUp(floor);
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        return elevatorApi.getFloorHeight();
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return elevatorApi.getElevatorNum();
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        return elevatorApi.getServicesFloors(elevatorNumber, floor);
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        return elevatorApi.getTarget(elevatorNumber);
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        elevatorApi.setCommittedDirection(elevatorNumber, direction);
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        elevatorApi.setServicesFloors(elevatorNumber, floor, service);
    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        elevatorApi.setTarget(elevatorNumber, target);
    }

    @Override
    public long getClockTick() throws RemoteException {
        return elevatorApi.getClockTick();
    }
}
