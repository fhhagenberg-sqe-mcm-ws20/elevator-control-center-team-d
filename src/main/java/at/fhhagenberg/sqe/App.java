package at.fhhagenberg.sqe;

import at.fhhagenberg.elevatorsys.ControlCenter;
import at.fhhagenberg.sqe.panes.ElevatorsPane;
import at.fhhagenberg.sqe.panes.FloorIndicatorBarPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

/**
 * JavaFX App
 */
public class App extends Application  {

    private ElevatorsPane elevatorPane;

    @Override
    public void start(Stage stage) {

        //TODO: create controlCenter and get Data to define screen Size
        ControlCenter controlCenter = new ControlCenter(mock(IElevator.class));

        int elevatorCount = controlCenter.getNumberOfElevators();
        int floorCount = controlCenter.getNumberOfFloors();

        var layout = new BorderPane();
        var innerLayout = new HBox();
        FloorIndicatorBarPane indicators = new FloorIndicatorBarPane(floorCount);
        elevatorPane = new ElevatorsPane(elevatorCount,floorCount);
        innerLayout.getChildren().addAll(indicators, elevatorPane);
        layout.setLeft(innerLayout);

        var scene = new Scene(layout, 1000, 700);
        stage.setScene(scene);
        stage.show();

        controlCenter.addPropertyChangeListener(elevatorPane);
        controlCenter.addPropertyChangeListener(indicators);
    }

    public static void main(String[] args) {
        launch();
    }

}