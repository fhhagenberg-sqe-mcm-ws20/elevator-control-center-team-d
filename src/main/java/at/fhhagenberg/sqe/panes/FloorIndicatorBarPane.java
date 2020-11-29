package at.fhhagenberg.sqe.panes;

import at.fhhagenberg.elevatorsys.models.BuildingModel;
import at.fhhagenberg.elevatorsys.models.ElevatorModel;
import at.fhhagenberg.elevatorsys.models.FloorModel;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloorIndicatorBarPane extends VBox implements PropertyChangeListener {
    //TODO: are floors just identified by index
    List<FloorIndicatorPane> floorIndicators = new ArrayList<FloorIndicatorPane>();
    public FloorIndicatorBarPane(int floorCount) {
        this.setHeight(floorCount*50);

        for(int i = 0; i < floorCount; i++){
            FloorIndicatorPane indicator = new FloorIndicatorPane(floorCount - i);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BuildingModel buildingModel = (BuildingModel) evt.getNewValue();
        List<FloorModel> floorModels = buildingModel.getFloors();

        for (int i = 0; i < floorIndicators.size(); i++) {
            floorIndicators.get(i).update(floorModels.get(i));
        }

    }
}
