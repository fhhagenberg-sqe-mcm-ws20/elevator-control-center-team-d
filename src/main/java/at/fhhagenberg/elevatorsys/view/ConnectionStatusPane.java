package at.fhhagenberg.elevatorsys.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ConnectionStatusPane extends VBox {
    private Rectangle connectionIndicator;
    private Text infoMessage;

    public ConnectionStatusPane(boolean connectionStatus){
        connectionIndicator = new Rectangle(15,15);
        connectionIndicator.setArcHeight(15);
        connectionIndicator.setArcWidth(15);
        connectionIndicator.setStroke(Color.BLACK);

        infoMessage = new Text();

        setConnectionStatus(connectionStatus);
        getChildren().addAll(connectionIndicator, infoMessage);
        setPadding(new Insets(5,5,5,5));
        //setAlignment(Pos.CENTER_LEFT);
        setWidth(40);
        setHeight(40);
    }

    public void setConnectionStatus(boolean connectionStatus) {
        if(connectionStatus){
            connectionIndicator.setFill(Color.GREEN);
            infoMessage.setText("connected");
        }
        else {
            connectionIndicator.setFill(Color.RED);
            infoMessage.setText("connection error");
        }
    }


}
