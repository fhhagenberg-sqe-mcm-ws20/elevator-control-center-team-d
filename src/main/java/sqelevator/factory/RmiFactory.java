package sqelevator.factory;

import sqelevator.IElevatorSystem;
import sqelevator.RmiElevatorAdapter;

public class RmiFactory implements ApiFactory {

    @Override
    public IElevatorSystem createElevatorApi() {
        return new RmiElevatorAdapter("rmi://localhost/ElevatorSim");
    }

}
