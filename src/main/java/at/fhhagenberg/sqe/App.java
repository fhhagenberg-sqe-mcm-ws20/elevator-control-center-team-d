package at.fhhagenberg.sqe;

import at.fhhagenberg.elevatorsys.ControlCenter;
import at.fhhagenberg.sqe.panes.ElevatorFloorPane;
import at.fhhagenberg.sqe.panes.ElevatorsPane;
import at.fhhagenberg.sqe.panes.FloorIndicatorBarPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        var innerLayout = new HBox();
        List<Integer> floorNumbers = new ArrayList<Integer>();
        floorNumbers.addAll(Arrays.asList(9,8,7,6,5,4,3,2,1,0));
        FloorIndicatorBarPane indicators = new FloorIndicatorBarPane(floorNumbers);

        elevatorPane = new ElevatorsPane(5,floorNumbers.size());

        innerLayout.getChildren().addAll(indicators, elevatorPane);
        layout.setLeft(innerLayout);

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