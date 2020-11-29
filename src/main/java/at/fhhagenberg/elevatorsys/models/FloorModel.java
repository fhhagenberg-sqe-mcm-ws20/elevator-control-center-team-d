package at.fhhagenberg.elevatorsys.models;

public class FloorModel {

    private boolean buttonDown;
    private boolean buttonUp;
    private final int floorNr;
    private final int floorHeight;

    public FloorModel(boolean buttonDown, boolean buttonUp, int floorNr, int floorHeight) {
        this.buttonDown = buttonDown;
        this.buttonUp = buttonUp;
        this.floorNr = floorNr;
        this.floorHeight = floorHeight;
    }

    public int getFloorHeight() {
        return floorHeight;
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
}
