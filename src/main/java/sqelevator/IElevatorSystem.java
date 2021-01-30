package sqelevator;

public interface IElevatorSystem {

    void connect();

    boolean isConnected();

    int getCommittedDirection(int elevatorNumber);

    int getElevatorAccel(int elevatorNumber);

    boolean getElevatorButton(int elevatorNumber, int floor);

    int getElevatorDoorStatus(int elevatorNumber);

    int getElevatorFloor(int elevatorNumber);

    int getElevatorNum();

    int getElevatorPosition(int elevatorNumber);

    int getElevatorSpeed(int elevatorNumber);

    int getElevatorWeight(int elevatorNumber);

    int getElevatorCapacity(int elevatorNumber);

    boolean getFloorButtonDown(int floor);

    boolean getFloorButtonUp(int floor);

    int getFloorHeight();

    int getFloorNum();

    boolean getServicesFloors(int elevatorNumber, int floor);

    int getTarget(int elevatorNumber);

    void setCommittedDirection(int elevatorNumber, int direction);

    void setServicesFloors(int elevatorNumber, int floor, boolean service);

    void setTarget(int elevatorNumber, int target);

    long getClockTick();

}
