package at.fhhagenberg.sqe.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ElevatorSpacerPane extends VBox {

    public ElevatorSpacerPane(int floorNumbers, double width) {
        this.setPrefWidth(width);
        this.setPrefHeight((double)floorNumbers*50);
        this.getChildren().add(new Separator());
        for(int i = 0; i < floorNumbers; i++){
            VBox placeholder = new VBox();
            placeholder.setPrefHeight(50);
            placeholder.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Color.DARKGRAY, Color.TRANSPARENT,
                    BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.DASHED, BorderStrokeStyle.NONE,
                    CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));
            this.getChildren().add(placeholder);
        }
    }
}
