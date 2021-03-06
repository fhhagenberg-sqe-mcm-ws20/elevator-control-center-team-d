package at.fhhagenberg.elevatorsys.view;

import at.fhhagenberg.elevatorsys.models.CommittedDirection;
import at.fhhagenberg.elevatorsys.models.DoorStatus;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

public class ElevatorBoxPane extends BorderPane {
    Rectangle doorLeft;
    Rectangle doorRight;
    Polyline arrowUp;
    Polyline arrowDown;

    public ElevatorBoxPane() {
        this.setPrefHeight(48);
        this.setPrefWidth(48);
        this.setStyle("-fx-background-color: #D3D3D3;");
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

        doorLeft = new Rectangle();
        doorLeft.setWidth(this.getPrefWidth()/5.0);
        doorLeft.setHeight(this.getPrefHeight());
        doorLeft.setFill(Color.RED);

        doorRight = new Rectangle();
        doorRight.setWidth(this.getPrefWidth()/5.0);
        doorRight.setHeight(this.getPrefHeight());
        doorRight.setFill(Color.RED);

        arrowUp = createArrowUp();
        arrowDown = createArrowDown();

        this.setLeft(doorLeft);
        this.setCenter(arrowDown);
        this.setRight(doorRight);
    }

    public void setDirection(CommittedDirection direction){
        switch (direction){
            case UP:
                this.setCenter(arrowUp);
                break;
            case DOWN:
                this.setCenter(arrowDown);
                break;
            case UNCOMMITTED:
                this.setCenter(null);
                break;
        }
    }

    public void setDoorStatus(DoorStatus doorStatus){
        switch (doorStatus){
            case OPEN:
                doorLeft.setFill(Color.GREEN);
                doorRight.setFill(Color.GREEN);
                break;
            case CLOSED:
                doorLeft.setFill(Color.RED);
                doorRight.setFill(Color.RED);
                break;
            default:
                doorLeft.setFill(Color.ORANGE);
                doorRight.setFill(Color.ORANGE);
        }
    }

    private Polyline createArrowUp(){
        Polyline arrow = new Polyline();
        arrow.getPoints().addAll(0.0, 10.0, 5.0, 0.0, 5.0, 20.0, 5.0, 0.0, 10.0, 10.0);
        return arrow;
    }

    private Polyline createArrowDown(){
        Polyline arrow = new Polyline();
        arrow.getPoints().addAll(0.0, 10.0, 5.0, 20.0, 5.0, 0.0, 5.0, 20.0, 10.0, 10.0);
        return arrow;
    }
}
