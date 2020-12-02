package at.fhhagenberg.elevatorsys.view;

import at.fhhagenberg.elevatorsys.models.FloorModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;

public class FloorIndicatorPane extends HBox {
    Polyline indicatorUp;
    Polyline indicatorDown;

    public FloorIndicatorPane(int floorNumber) {
        this.setPrefHeight(50);

        Label floorNumberLabel = new Label(String.valueOf(floorNumber));
        floorNumberLabel.setPrefWidth(30);
        floorNumberLabel.setAlignment(Pos.CENTER_RIGHT);
        floorNumberLabel.setPadding(new Insets(0,3,0,0));
        floorNumberLabel.setFont(new Font(20));

        indicatorUp = new Polyline();
        indicatorUp.getPoints().addAll(10.0, 0.0,  0.0, 10.0, 20.0, 10.0, 10.0, 0.0);
        indicatorUp.setFill(Color.LIGHTGRAY);
        indicatorUp.setStroke(Color.BLACK);

        indicatorDown = new Polyline();
        indicatorDown.getPoints().addAll(10.0, 10.0,  0.0, 0.0, 20.0, 0.0, 10.0, 10.0);
        indicatorDown.setFill(Color.LIGHTGRAY);
        indicatorDown.setStroke(Color.BLACK);

        VBox indicatorLayout = new VBox(indicatorUp, indicatorDown);
        indicatorLayout.setAlignment(Pos.CENTER);

        this.getChildren().addAll(floorNumberLabel, indicatorLayout);
        this.setPadding(new Insets(0,5,0,5));
        this.setAlignment(Pos.CENTER_LEFT);
    }

    public void update(FloorModel floorModel) {
        setUpActivated(floorModel.isButtonUp());
        setDownActivated(floorModel.isButtonDown());
    }

    private void setUpActivated(boolean activated){
        if(activated) {
            indicatorUp.setFill(Color.LIGHTGREEN);
        }
        else {
            indicatorUp.setFill(Color.LIGHTGRAY);
        }
    }

    private void setDownActivated(boolean activated) {
        if (activated) {
            indicatorDown.setFill(Color.LIGHTGREEN);
        } else {
            indicatorDown.setFill(Color.LIGHTGRAY);
        }
    }
}
