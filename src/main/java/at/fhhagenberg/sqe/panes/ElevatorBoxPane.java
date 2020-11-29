package at.fhhagenberg.sqe.panes;

import javafx.geometry.Pos;
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
        //this.setMaxSize(35,35);
        //this.setMinSize(35,35);
        //this.setHeight(35);
        //this.setWidth(35);
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

    public void setDirection(int direction){
        if(direction > 0){
            this.setCenter(arrowUp);
        }
        else if (direction < 0){
            this.setCenter(arrowDown);
        }
        else {
            this.setCenter(null);
        }
    }

    public void setDoorStatus(int doorStatus){
        if(doorStatus == 1){    //TODO: 1 equals open is just an assumption; adapt when real values are known
            doorLeft.setFill(Color.GREEN);
            doorRight.setFill(Color.GREEN);
        }
        else {
            doorLeft.setFill(Color.RED);
            doorRight.setFill(Color.RED);
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
