package at.fhhagenberg.elevatorsys;

import at.fhhagenberg.elevatorsys.view.ControlCenterUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sqelevator.IElevatorSystem;
import sqelevator.factory.ApiFactory;
import sqelevator.factory.RmiFactory;

import java.rmi.RemoteException;

/**
 * JavaFX App
 */
public class App extends Application  {

    private final ApiFactory apiFactory;

    private ControlCenter controlCenter;
    private IElevatorSystem elevatorApi;

    public App() {
        apiFactory = new RmiFactory();
    }

    public App(ApiFactory apiFactory) {
        this.apiFactory = apiFactory;
    }

    @Override
    public void start(Stage stage) {
        elevatorApi = apiFactory.createElevatorApi();
        elevatorApi.connect();
        controlCenter = new ControlCenter(elevatorApi);
        ControlCenterUI controlCenterUI = new ControlCenterUI(controlCenter);

        Runnable updateRunnable = () -> {
            while(true) {
                Platform.runLater(() -> {
                    try {
                        controlCenter.updateBuilding();
                    } catch (RemoteException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                });
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        };

        Thread updateThread = new Thread(updateRunnable);
        updateThread.setDaemon(true);
        updateThread.start();

        //Todo: make windowSize dynamic?
        var scene = new Scene(controlCenterUI, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Elevator Control Center");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public ControlCenter getControlCenter() {
        return controlCenter;
    }

    public IElevatorSystem getElevatorApi() {
        return elevatorApi;
    }
}