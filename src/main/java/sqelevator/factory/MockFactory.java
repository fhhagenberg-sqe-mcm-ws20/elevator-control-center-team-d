package sqelevator.factory;

import sqelevator.ElevatorMock;
import sqelevator.IElevatorSystem;

public class MockFactory implements ApiFactory {

    @Override
    public IElevatorSystem createElevatorApi() {
        return new ElevatorMock();
    }

}
