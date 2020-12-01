package at.fhhagenberg.sqe.panes;

import at.fhhagenberg.elevatorsys.models.BuildingModel;
import at.fhhagenberg.elevatorsys.models.ElevatorModel;
import javafx.scene.layout.HBox;

import java.util.List;

public class ElevatorsPane extends HBox {

    ElevatorPane[] elevators;
    private final int numberOfElevators;
    private final int numberOfFloors;

    public ElevatorsPane(int numberOfElevators, int numberOfFloors) {
        this.numberOfElevators = numberOfElevators;
        this.numberOfFloors = numberOfFloors;
        elevators = new ElevatorPane[numberOfElevators];

        for (int i = 0; i < numberOfElevators; i++) {
            elevators[i] = new ElevatorPane(numberOfFloors);
        }
        this.getChildren().addAll(elevators);
    }

    public void updateView(BuildingModel buildingModel) {
        List<ElevatorModel> elevatorModels = buildingModel.getElevators();

        for (int i = 0; i < numberOfElevators; i++) {
            elevators[i].update(elevatorModels.get(i));
        }
    }

}
