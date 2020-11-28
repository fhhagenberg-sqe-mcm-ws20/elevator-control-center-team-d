package at.fhhagenberg.sqe.panes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DummyElevatorShaft extends Rectangle {

    public DummyElevatorShaft(int floorNumber) {
        this.setHeight(floorNumber*50);
        this.setWidth(40);
        this.setFill(Color.LIGHTGRAY);
    }
}
