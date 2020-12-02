package at.fhhagenberg.elevatorsys;

import at.fhhagenberg.elevatorsys.ControlCenter;
import at.fhhagenberg.elevatorsys.models.*;
import at.fhhagenberg.elevatorsys.view.ControlCenterUI;
import at.fhhagenberg.elevatorsys.view.ElevatorsPane;
import at.fhhagenberg.sqe.IElevator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application  {

    private ElevatorsPane elevatorPane;

    @Override
    public void start(Stage stage) {
        //TODO: Make a API Factory which creates the elevator interface
        ControlCenter controlCenter;
        try {
            IElevator elevatorApi =  (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
            controlCenter = new ControlCenter(elevatorApi);
        } catch (Exception e) {
            controlCenter = new ControlCenter(createDummyData());
        }

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
        FloorModel floor6 = new FloorModel(false, true, 6, 50);
        FloorModel floor7 = new FloorModel(false, false, 7, 50);
        FloorModel floor8 = new FloorModel(false, false, 8, 50);
        FloorModel floor9 = new FloorModel(true, false, 9, 50);
        FloorModel floor10 = new FloorModel(false, false, 10, 50);
        FloorModel floor11 = new FloorModel(false, false, 11, 50);

        List<FloorModel> floors = new ArrayList<>();
        floors.add(floor0);
        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
        floors.add(floor4);
        floors.add(floor5);
        floors.add(floor6);
        floors.add(floor7);
        floors.add(floor8);
        floors.add(floor9);
        floors.add(floor10);
        floors.add(floor11);


        List<Integer> servicedFloors = new ArrayList<>();
        servicedFloors.add(0);
        servicedFloors.add(1);
        servicedFloors.add(2);
        servicedFloors.add(3);
        servicedFloors.add(4);
        servicedFloors.add(5);
        servicedFloors.add(6);
        servicedFloors.add(7);
        servicedFloors.add(8);
        servicedFloors.add(9);
        servicedFloors.add(10);
        servicedFloors.add(11);

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
        List<Integer> selectedFloors = new ArrayList<>();
        selectedFloors.add(2);
        selectedFloors.add(3);
        elevator0.setSelectedFloors(selectedFloors);

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

        return new BuildingModel(elevators, floors);
    }

}