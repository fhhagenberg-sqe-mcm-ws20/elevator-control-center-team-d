package at.fhhagenberg.sqe.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class FloorPane extends HBox {

    private final Rectangle floorRect;
    private final Circle lightCircle;

    public FloorPane() {
        floorRect = new Rectangle();
        floorRect.setWidth(49);
        floorRect.setHeight(49);
        floorRect.setFill(Color.DARKGRAY);
        floorRect.setStrokeWidth(0.5);
        floorRect.setStroke(Color.BLACK);

        Rectangle bufferRect = new Rectangle();
        bufferRect.setWidth(5);
        bufferRect.setHeight(1);
        bufferRect.setFill(Color.WHITE);

        lightCircle = new Circle();
        lightCircle.setRadius(5f);
        lightCircle.setFill(Color.BLACK);
        HBox underlineBox = new HBox(bufferRect, lightCircle);
        underlineBox.setAlignment(Pos.CENTER_LEFT);
        underlineBox.setPrefWidth(50);
        underlineBox.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Color.DARKGRAY, Color.TRANSPARENT,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.DASHED, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));

        this.getChildren().addAll(floorRect, underlineBox);
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
