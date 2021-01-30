package at.fhhagenberg.elevatorsys.models;

import sqelevator.IElevatorSystem;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ModeManager {
    private Map<Mode, ModeBase> modes = new EnumMap<>(Mode.class);
    Map<Integer, Mode> modeMap = new HashMap<>();

    public ModeManager(IElevatorSystem elevatorSystem){
        modes.put(Mode.MANUAL, new ManualMode(elevatorSystem));
        modes.put(Mode.AUTOMATIC, new AutomaticMode(elevatorSystem));
        for (int i = 0; i < elevatorSystem.getElevatorNum(); i++) {
            modeMap.put(i, Mode.MANUAL);
        }
    }

    public void setModeForElevator(Mode mode, int elevatorNr) {
        modeMap.put(elevatorNr, mode);
    }

    public Mode getModeForElevator(int elevatorNr) {
        return modeMap.get(elevatorNr);
    }

    public void execute(BuildingModel buildingModel) {
        buildingModel.getElevators().forEach(elevatorModel ->
            modes.get(modeMap.get(elevatorModel.getElevatorNumber())).execute(elevatorModel)
        );
    }
}
