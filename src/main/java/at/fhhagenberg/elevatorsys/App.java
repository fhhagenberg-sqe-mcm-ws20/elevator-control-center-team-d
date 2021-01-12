package at.fhhagenberg.elevatorsys;

import at.fhhagenberg.elevatorsys.ControlCenter;
import at.fhhagenberg.elevatorsys.models.*;
import at.fhhagenberg.elevatorsys.view.ControlCenterUI;
import at.fhhagenberg.elevatorsys.view.ElevatorsPane;
import at.fhhagenberg.sqe.ElevatorMock;
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
            IElevator elevatorMock = new ElevatorMock();
            controlCenter = new ControlCenter(elevatorMock);
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
}