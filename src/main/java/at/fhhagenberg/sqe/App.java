package at.fhhagenberg.sqe;

import at.fhhagenberg.elevatorsys.ControlCenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * JavaFX App
 */
public class App extends Application implements PropertyChangeListener {

    private ElevatorFloorPane elevator;
    private int ran = 0;
    private ElevatorsPane elevatorPane;

    @Override
    public void start(Stage stage) {

        //TODO: create controlCenter and get Data to define screen Size
        //ControlCenter controlCenter = new ControlCenter(new IElevator()); // Need an Elevator Class for that

        var layout = new BorderPane();
        elevatorPane = new ElevatorsPane(3,7);
        layout.setLeft(elevatorPane);

        var scene = new Scene(layout, 1000, 700);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}