package at.fhhagenberg.elevatorsys;

public class FloorModel {

    public static final int FLOOR_HEIGHT = 350;

    private boolean buttonDown;
    private boolean buttonUp;
    private int floorNr;

    public FloorModel(boolean buttonDown, boolean buttonUp, int floorNr) {
        this.buttonDown = buttonDown;
        this.buttonUp = buttonUp;
        this.floorNr = floorNr;
    }

    public static int getFloorHeight() {
        return FLOOR_HEIGHT;
    }

    public boolean isButtonDown() {
        return buttonDown;
    }

    public void setButtonDown(boolean buttonDown) {
        this.buttonDown = buttonDown;
    }

    public boolean isButtonUp() {
        return buttonUp;
    }

    public void setButtonUp(boolean buttonUp) {
        this.buttonUp = buttonUp;
    }

    public int getFloorNr() {
        return floorNr;
    }

    public void setFloorNr(int floorNr) {
        this.floorNr = floorNr;
    }
}
