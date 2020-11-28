package at.fhhagenberg.sqe.panes;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloorIndicatorBarPane extends VBox {
    //TODO: just with floor just identified by index?
    Map<Integer, FloorIndicatorPane> floorIndicators = new HashMap<Integer, FloorIndicatorPane>();
    public FloorIndicatorBarPane(List<Integer> floorNumbers) {
        this.setHeight(floorNumbers.size()*50);

        for(Integer i : floorNumbers){
            FloorIndicatorPane indicator = new FloorIndicatorPane(i);
            indicator.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Color.DARKGRAY, Color.TRANSPARENT,
                                            BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.DASHED, BorderStrokeStyle.NONE,
                                            CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));
            this.getChildren().add(indicator);
            floorIndicators.put(i, indicator);
        }
    }

    public FloorIndicatorPane getFloorIndicator(int floorNumber){
        return floorIndicators.get(floorNumber);
    }
}
