package sqelevator;

public interface IElevatorSystem {

    //TODO: map RemoteException to ElevatorSystemException, maybe subclass from IOException

    void connect();

    int getCommittedDirection(int elevatorNumber) throws java.rmi.RemoteException;

    int getElevatorAccel(int elevatorNumber) throws java.rmi.RemoteException;

    boolean getElevatorButton(int elevatorNumber, int floor) throws java.rmi.RemoteException;

    int getElevatorDoorStatus(int elevatorNumber) throws java.rmi.RemoteException;

    int getElevatorFloor(int elevatorNumber) throws java.rmi.RemoteException;

    int getElevatorNum() throws java.rmi.RemoteException;

    int getElevatorPosition(int elevatorNumber) throws java.rmi.RemoteException;

    int getElevatorSpeed(int elevatorNumber) throws java.rmi.RemoteException;

    int getElevatorWeight(int elevatorNumber) throws java.rmi.RemoteException;

    int getElevatorCapacity(int elevatorNumber) throws java.rmi.RemoteException;

    boolean getFloorButtonDown(int floor) throws java.rmi.RemoteException;

    boolean getFloorButtonUp(int floor) throws java.rmi.RemoteException;

    int getFloorHeight() throws java.rmi.RemoteException;

    int getFloorNum() throws java.rmi.RemoteException;

    boolean getServicesFloors(int elevatorNumber, int floor) throws java.rmi.RemoteException;

    int getTarget(int elevatorNumber) throws java.rmi.RemoteException;

    void setCommittedDirection(int elevatorNumber, int direction) throws java.rmi.RemoteException;

    void setServicesFloors(int elevatorNumber, int floor, boolean service) throws java.rmi.RemoteException;

    void setTarget(int elevatorNumber, int target) throws java.rmi.RemoteException;

    long getClockTick() throws java.rmi.RemoteException;

}
