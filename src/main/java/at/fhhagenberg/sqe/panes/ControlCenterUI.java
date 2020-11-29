package at.fhhagenberg.sqe.panes;

import at.fhhagenberg.elevatorsys.ControlCenter;
import javafx.scene.layout.HBox;

public class ControlCenterUI extends HBox {
    private final FloorIndicatorBarPane floorIndicators;
    private final ElevatorsPane elevators;

    public ControlCenterUI(int floorAmount, int elevatorAmount, ControlCenter controlCenter) {
        floorIndicators = new FloorIndicatorBarPane(floorAmount);
        elevators = new ElevatorsPane(elevatorAmount, floorAmount);
        controlCenter.addPropertyChangeListener(floorIndicators);
        controlCenter.addPropertyChangeListener(elevators);

        this.getChildren().addAll(floorIndicators, elevators);
    }
}
