package at.fhhagenberg.elevatorsys.view;

import at.fhhagenberg.elevatorsys.models.BuildingModel;
import at.fhhagenberg.elevatorsys.models.FloorModel;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class FloorIndicatorBarPane extends VBox {
    List<FloorIndicatorPane> floorIndicators = new ArrayList<>();
    public FloorIndicatorBarPane(int floorCount) {
        this.setHeight((double)floorCount*50);

        for(int i = 0; i < floorCount; i++){
            FloorIndicatorPane indicator = new FloorIndicatorPane(floorCount - i - 1);
            indicator.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Color.DARKGRAY, Color.TRANSPARENT,
                                            BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.DASHED, BorderStrokeStyle.NONE,
                                            CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));
            this.getChildren().add(indicator);
            floorIndicators.add(indicator);
        }
    }

    public FloorIndicatorPane getFloorIndicator(int floorNumber){
        return floorIndicators.get(floorNumber);
    }

    public void updateView(BuildingModel buildingModel) {
        List<FloorModel> floorModels = buildingModel.getFloors();

        for (int i = 0; i < floorIndicators.size(); i++) {
            floorIndicators.get(i).update(floorModels.get(i));
        }
    }
}
