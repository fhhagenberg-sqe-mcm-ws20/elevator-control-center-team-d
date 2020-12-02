package at.fhhagenberg.elevatorsys.view;

import at.fhhagenberg.elevatorsys.models.BuildingModel;
import at.fhhagenberg.elevatorsys.models.ElevatorModel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


import java.util.List;

public class ElevatorsPane extends HBox {

    ElevatorPane[] elevators;
    private final int numberOfElevators;
    private final int numberOfFloors;

    public ElevatorsPane(int numberOfElevators, int numberOfFloors, EventHandler<MouseEvent> eventHandler) {
        this.numberOfElevators = numberOfElevators;
        this.numberOfFloors = numberOfFloors;
        elevators = new ElevatorPane[numberOfElevators];

        for (int i = 0; i < numberOfElevators; i++) {
            elevators[i] = new ElevatorPane(i, numberOfFloors, eventHandler);
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
