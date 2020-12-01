package at.fhhagenberg.sqe;

import at.fhhagenberg.elevatorsys.ControlCenter;
import at.fhhagenberg.elevatorsys.models.*;
import at.fhhagenberg.sqe.panes.ControlCenterUI;
import at.fhhagenberg.sqe.panes.ElevatorsPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application  {

    private ElevatorsPane elevatorPane;

    @Override
    public void start(Stage stage) {

        //TODO: initiate ControlCenter with Elevator Interface instead of dummy data
        ControlCenter controlCenter = new ControlCenter(createDummyData());

        ControlCenterUI controlCenterUI = new ControlCenterUI(controlCenter);
        var scene = new Scene(controlCenterUI, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Elevator Control Center");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private BuildingModel createDummyData() {
        FloorModel floor0 = new FloorModel(false, true, 0, 50);
        FloorModel floor1 = new FloorModel(false, false, 1, 50);
        FloorModel floor2 = new FloorModel(false, false, 2, 50);
        FloorModel floor3 = new FloorModel(true, false, 3, 50);
        FloorModel floor4 = new FloorModel(false, false, 4, 50);
        FloorModel floor5 = new FloorModel(false, false, 5, 50);

        List<FloorModel> floors = new ArrayList<>();
        floors.add(floor0);
        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
        floors.add(floor4);
        floors.add(floor5);


        List<Integer> servicedFloors = new ArrayList<>();
        servicedFloors.add(0);
        servicedFloors.add(1);
        servicedFloors.add(2);
        servicedFloors.add(3);
        servicedFloors.add(4);
        servicedFloors.add(5);

        ElevatorModel elevator0 = new ElevatorModel(CommittedDirection.UNCOMMITTED,
                DoorStatus.OPEN,
                12,
                100,
                0,
                0,
                0,
                120,
                0,
                servicedFloors);

        ElevatorModel elevator1 = new ElevatorModel(CommittedDirection.UP,
                DoorStatus.CLOSED,
                12,
                100,
                0,
                0,
                5,
                120,
                3,
                servicedFloors);

        ElevatorModel elevator2 = new ElevatorModel(CommittedDirection.DOWN,
                DoorStatus.CLOSED,
                12,
                100,
                3,
                0,
                5,
                120,
                1,
                servicedFloors);

        List<ElevatorModel> elevators = new ArrayList<>();
        elevators.add(elevator0);
        elevators.add(elevator1);
        elevators.add(elevator2);

        return new BuildingModel(elevators, floors);;
    }

}