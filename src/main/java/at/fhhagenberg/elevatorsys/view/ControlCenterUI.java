package at.fhhagenberg.elevatorsys.view;

import at.fhhagenberg.elevatorsys.ControlCenter;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControlCenterUI extends HBox implements PropertyChangeListener {
    private final FloorIndicatorBarPane floorIndicators;
    private final ElevatorsPane elevators;
    private final ConnectionStatusPane connectionStatusPane;
    private final ControlCenter controlCenter;

    public ControlCenterUI(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
        controlCenter.addChangeListener(this);
        floorIndicators = new FloorIndicatorBarPane(controlCenter.getNumberOfFloors());
        elevators = new ElevatorsPane(controlCenter.getNumberOfElevators(), controlCenter.getNumberOfFloors(), controlCenter);
        connectionStatusPane = new ConnectionStatusPane(false);
        updateUI();

        this.getChildren().addAll(floorIndicators, elevators, connectionStatusPane);
    }

    private void updateUI() {
        if(controlCenter.getConnectionStatus()){
            floorIndicators.updateView(controlCenter.getBuildingModel());
            elevators.updateView(controlCenter.getBuildingModel());
        }
        connectionStatusPane.setConnectionStatus(controlCenter.getConnectionStatus());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateUI();
    }
}
