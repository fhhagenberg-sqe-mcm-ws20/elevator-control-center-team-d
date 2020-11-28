package at.fhhagenberg.sqe.panes;

import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class FloorPane extends FlowPane {

    private final Rectangle floorRect;
    private final Circle lightCircle;

    public FloorPane() {
        floorRect = new Rectangle();
        floorRect.setWidth(50);
        floorRect.setHeight(50);
        floorRect.setFill(Color.DARKGRAY);
        floorRect.setStroke(Color.BLACK);

        Rectangle bufferRect = new Rectangle();
        bufferRect.setWidth(5);
        bufferRect.setHeight(1);
        bufferRect.setFill(Color.WHITE);

        lightCircle = new Circle();
        lightCircle.setRadius(5f);
        lightCircle.setFill(Color.BLACK);
        this.getChildren().addAll(floorRect, bufferRect,lightCircle);
    }

    public void setFloor() {
        floorRect.setFill(Color.LIGHTGRAY);
    }

    public void unsetFloor() {
        floorRect.setFill(Color.DARKGRAY);
    }

    public void setLight() {
        lightCircle.setFill(Color.GREEN);
    }

    public void unsetLight() {
        lightCircle.setFill(Color.BLACK);
    }

}
