package at.fhhagenberg.elevatorsys;

import at.fhhagenberg.elevatorsys.view.ControlCenterUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sqelevator.IElevatorSystem;
import sqelevator.factory.ApiFactory;

/**
 * JavaFX App
 */
public class App extends Application  {

    private final ApiFactory apiFactory;

    private ControlCenter controlCenter;
    private IElevatorSystem elevatorApi;

    public App(ApiFactory apiFactory) {
        this.apiFactory = apiFactory;
    }

    @Override
    public void start(Stage stage) {
        elevatorApi = apiFactory.createElevatorApi();
        elevatorApi.connect();
        controlCenter = new ControlCenter(elevatorApi);
        ControlCenterUI controlCenterUI = new ControlCenterUI(controlCenter);
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