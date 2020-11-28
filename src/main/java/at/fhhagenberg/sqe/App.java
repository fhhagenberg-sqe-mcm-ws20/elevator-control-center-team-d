package at.fhhagenberg.sqe;

import at.fhhagenberg.sqe.panes.ElevatorFloorPane;
import at.fhhagenberg.sqe.panes.ElevatorPane;
import at.fhhagenberg.sqe.panes.ElevatorsPane;
import at.fhhagenberg.sqe.panes.FloorPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

/**
 * JavaFX App
 */
public class App extends Application {

    private ElevatorFloorPane elevator;
    private int ran = 0;
    private ElevatorsPane elevatorPane;

    @Override
    public void start(Stage stage) {

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

}