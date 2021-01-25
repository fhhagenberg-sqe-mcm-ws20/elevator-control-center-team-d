package sqelevator;

import at.fhhagenberg.elevatorsys.App;
import at.fhhagenberg.elevatorsys.ControlCenter;
import at.fhhagenberg.elevatorsys.view.FloorPane;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import sqelevator.factory.MockFactory;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(ApplicationExtension.class)
class GUIApplicationTest {

    private IElevatorSystem elevatorAPI;
    private ControlCenter controlCenter;

    @Start
    public void start(Stage stage) {
        App app = new App(new MockFactory());
        app.start(stage);
        elevatorAPI = app.getElevatorApi();
        controlCenter = app.getControlCenter();
    }

    /**
     * Sets target floor in backend and checks on UI
     * @param robot
     * @throws RemoteException
     */
    @Test
    public void testSetTargetFloor(FxRobot robot) throws RemoteException {
        elevatorAPI.setTarget(0, 3);
        elevatorAPI.setTarget(1, 4);
        elevatorAPI.setTarget(2, 5);

        Platform.runLater(() -> {
            try {
                controlCenter.updateBuilding();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        robot.sleep(1000);

        Circle circleElevator0 = robot
                .lookup("#elevator0")
                .lookup("#floor3")
                .lookup("#targetLight")
                .queryAs(Circle.class);

        Circle circleElevator1 = robot
                .lookup("#elevator1")
                .lookup("#floor4")
                .lookup("#targetLight")
                .queryAs(Circle.class);

        Circle circleElevator2 = robot
                .lookup("#elevator2")
                .lookup("#floor5")
                .lookup("#targetLight")
                .queryAs(Circle.class);
        assertEquals(Color.GREEN, circleElevator0.getFill());
        assertEquals(Color.GREEN, circleElevator1.getFill());
        assertEquals(Color.GREEN, circleElevator2.getFill());
    }


    /**
     * Clicks on floor in GUI and checks if backend updated
     * @param fxRobot
     */
    @Test
    public void testElevatorFloorSelection(FxRobot fxRobot) {
        FloorPane elevator0floor8 = fxRobot.lookup("#elevator0").lookup("#floor8").queryAs(FloorPane.class);
        fxRobot.clickOn(elevator0floor8);

        FloorPane elevator1floor5 = fxRobot.lookup("#elevator1").lookup("#floor5").queryAs(FloorPane.class);
        fxRobot.clickOn(elevator1floor5);

        FloorPane elevator2floor3 = fxRobot.lookup("#elevator2").lookup("#floor3").queryAs(FloorPane.class);
        fxRobot.clickOn(elevator2floor3);

        Platform.runLater(() -> {
            try {
                controlCenter.updateBuilding();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        fxRobot.sleep(500);
        int currentFloorTargetElevator0 = controlCenter.getBuildingModel().getElevator(0).getCurrentFloorTarget();
        assertEquals(8, currentFloorTargetElevator0);

        int currentFloorTargetElevator1 = controlCenter.getBuildingModel().getElevator(1).getCurrentFloorTarget();
        assertEquals(5, currentFloorTargetElevator1);

        int currentFloorTargetElevator2 = controlCenter.getBuildingModel().getElevator(2).getCurrentFloorTarget();
        assertEquals(3, currentFloorTargetElevator2);
    }

}

// Set style class instead of setting color directly for target lights. With the name ("targeted")
// call control center update building on fx thread and then wait until that is executed -> use fxRobot.interact
// Use App in test

