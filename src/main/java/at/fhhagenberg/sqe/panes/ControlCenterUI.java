package at.fhhagenberg.sqe.panes;

import at.fhhagenberg.elevatorsys.ControlCenter;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControlCenterUI extends HBox implements PropertyChangeListener {
    private final FloorIndicatorBarPane floorIndicators;
    private final ElevatorsPane elevators;

    private ControlCenter controlCenter;

    public ControlCenterUI(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
        controlCenter.addChangeListener(this);
        floorIndicators = new FloorIndicatorBarPane(controlCenter.getNumberOfFloors());
        elevators = new ElevatorsPane(controlCenter.getNumberOfElevators(), controlCenter.getNumberOfFloors());
        updateUI();

        this.getChildren().addAll(floorIndicators, elevators);
    }

    private void updateUI() {
        floorIndicators.updateView(controlCenter.getBuildingModel());
        elevators.updateView(controlCenter.getBuildingModel());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateUI();
    }
}
