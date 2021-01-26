package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevatorSystem;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ModeManager {
    private Map<Mode, ModeBase> modes = new HashMap<>();
    Map<Integer, Mode> modeMap = new HashMap<>();

    public ModeManager(IElevatorSystem elevatorSystem){
        modes.put(Mode.MANUAL, new ManualMode(elevatorSystem));
        modes.put(Mode.AUTOMATIC, new AutomaticMode(elevatorSystem));

        try {
            for (int i = 0; i < elevatorSystem.getElevatorNum(); i++) {
                modeMap.put(i, Mode.MANUAL);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setModeForElevator(Mode mode, int elevatorNr) {
        modeMap.put(elevatorNr, mode);
    }

    public Mode getModeForElevator(int elevatorNr) {
        return modeMap.get(elevatorNr);
    }

    public void execute(BuildingModel buildingModel) {
        buildingModel.getElevators().forEach(elevatorModel -> {
            try {
                modes.get(modeMap.get(elevatorModel.getElevatorNumber())).execute(elevatorModel);
            } catch (RemoteException e) {
                System.out.println(e.getLocalizedMessage());
            }
        });
    }
}
