package at.fhhagenberg.elevatorsys;

import at.fhhagenberg.elevatorsys.models.CommittedDirection;
import at.fhhagenberg.elevatorsys.models.DoorStatus;

public class ControlCenterViewModel {

    public int getNumberOfFloors() {
        return 4;
    }

    public int getNumberOfElevators() {
        return 3;
    }

    public boolean automaticControl(int elevatorNumber) {
        return true;
    }

    /**
     * @return floor number of the current elevator position.
     */
    public int getElevatorPosition(int elevatorNumber) {
        return 2;
    }

    /**
     * @return enum value for the current door status of the chosen elevator
     */
    public DoorStatus getDoorStatus(int elevatorNumber) {
        return DoorStatus.CLOSED;
    }

    /**
     * @return floor number of the current target
     */
    public int getTarget(int elevatorNumber) {
        return 1;
    }

    /**
     * @return enum value of the committed direction of the chosen elevator
     */
    public CommittedDirection getCommittedDirection(int elevatorNumber) {
        return CommittedDirection.UNCOMMITTED;
    }

    /**
     * @return array of floor numbers which are selected in a certain elevator
     */
    public int[] getSelectedFloors(int elevatorNumber) {
        return new int[]{0,2};
    }

    /**
     * @return state of of floor button up/down in certain floor(used enum with up, down, uncommitted).
     */
    public CommittedDirection getFloorButtonDirection(int floorNumber) {
        return CommittedDirection.UP;
    }

    /**
     * @return array of floor numbers which are not serviced by a certain elevator
     */
    public int[] getNotServicedFloors(int elevatorNumber) {
        return new int[]{3};
    }

}
