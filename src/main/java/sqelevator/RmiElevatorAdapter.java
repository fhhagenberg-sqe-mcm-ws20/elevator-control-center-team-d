package sqelevator;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class RmiElevatorAdapter implements IElevatorSystem {

    private IElevator elevatorApi;
    private boolean connected = false;
    private String lookupName;

    public RmiElevatorAdapter(String lookupName) {
        this.lookupName = lookupName;
        connect();
    }

    @Override
    public void connect() {
        try {
            elevatorApi = (IElevator) Naming.lookup(lookupName);
            connected = true;
        } catch (Exception e) {
            connected = false;
        }
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) {
        try {
            return elevatorApi.getCommittedDirection(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) {
        try {
            return elevatorApi.getElevatorAccel(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) {
        try {
            return elevatorApi.getElevatorButton(elevatorNumber, floor);
        } catch (RemoteException e) {
            reconnect();
            return false;
        }
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) {
        try {
            return elevatorApi.getElevatorDoorStatus(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) {
        try {
            return elevatorApi.getElevatorFloor(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public int getElevatorNum() {
        try {
            return elevatorApi.getElevatorNum();
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) {
        try {
            return elevatorApi.getElevatorPosition(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) {
        try {
            return elevatorApi.getElevatorSpeed(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) {
        try {
            return elevatorApi.getElevatorWeight(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) {
        try {
            return elevatorApi.getElevatorCapacity(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public boolean getFloorButtonDown(int floor) {
        try {
            return elevatorApi.getFloorButtonDown(floor);
        } catch (RemoteException e) {
            reconnect();
            return false;
        }
    }

    @Override
    public boolean getFloorButtonUp(int floor) {
        try {
            return elevatorApi.getFloorButtonUp(floor);
        } catch (RemoteException e) {
            reconnect();
            return false;
        }
    }

    @Override
    public int getFloorHeight() {
        try {
            return elevatorApi.getFloorHeight();
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public int getFloorNum() {
        try {
            return elevatorApi.getFloorNum();
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) {
        try {
            return elevatorApi.getServicesFloors(elevatorNumber, floor);
        } catch (RemoteException e) {
            reconnect();
            return false;
        }
    }

    @Override
    public int getTarget(int elevatorNumber) {
        try {
            return elevatorApi.getTarget(elevatorNumber);
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) {
        try {
            elevatorApi.setCommittedDirection(elevatorNumber, direction);
        } catch (RemoteException e) {
            reconnect();
        }
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) {
        try {
            elevatorApi.setServicesFloors(elevatorNumber, floor, service);
        } catch (RemoteException e) {
            reconnect();
        }
    }

    @Override
    public void setTarget(int elevatorNumber, int target) {
        try {
            elevatorApi.setTarget(elevatorNumber, target);
        } catch (RemoteException e) {
            reconnect();
        }
    }

    @Override
    public long getClockTick() {
        try {
            return elevatorApi.getClockTick();
        } catch (RemoteException e) {
            reconnect();
            return 0;
        }
    }

    public void reconnect() {
        connected = false;
        Runnable runnable = () -> {
            while (!connected) {
                connect();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getLocalizedMessage());
                    Thread.currentThread().interrupt();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }

}
