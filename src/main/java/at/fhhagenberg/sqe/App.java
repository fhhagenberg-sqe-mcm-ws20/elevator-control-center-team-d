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

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var layout = new BorderPane(label);
        var button = new Button("Click me!");
        button.setOnAction(evt -> button.setText("Clicked!"));
        layout.setBottom(button);

        //TODO: create controlCenter and get Data to define screen Size
        //ControlCenter controlCenter = new ControlCenter(new IElevator()); // Need an Elevator Class for that


        var scene = new Scene(layout, 640, 480);

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