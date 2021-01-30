package sqelevator.factory;

import sqelevator.IElevatorSystem;
import sqelevator.RmiElevatorAdapter;

public interface ApiFactory {
    default IElevatorSystem createElevatorApi() {
        return new RmiElevatorAdapter( "rmi://localhost/ElevatorSim");
    }
}
