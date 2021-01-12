package at.fhhagenberg.sqe;

import at.fhhagenberg.elevatorsys.ControlCenter;
import at.fhhagenberg.elevatorsys.view.ControlCenterUI;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.rmi.RemoteException;


@ExtendWith(ApplicationExtension.class)
class GUIApplicationTest {

    private IElevator elevatorAPI;
    private ControlCenter controlCenter;


    @Start
    public void start(Stage stage) {
        elevatorAPI = new ElevatorMock();
        controlCenter = new ControlCenter(elevatorAPI);
        ControlCenterUI controlCenterUI = new ControlCenterUI(controlCenter);
        var scene = new Scene(controlCenterUI, 500, 700);
        stage.setScene(scene);
        stage.setTitle("Elevator Control Center");
        stage.show();
    }

    @Test
    public void test1(FxRobot robot) throws RemoteException {
        robot.sleep(1000);
        elevatorAPI.setTarget(1, 5);

        Platform.runLater(() -> {
            try {
                controlCenter.updateBuilding();
                System.out.println("------------");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        robot.sleep(5000);
    }



}
