package backend.model.simulation.administration.data;

import backend.model.simulables.Simulable;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.utils.MathUtils;

import java.util.List;
import java.util.stream.IntStream;

public class SimulationFollowAdministrator {

    public static void followSimulable(Simulable simulable){
        if(!SimulationDataAdministrator.getSimulationData().getFollowedSimulables().contains(simulable))
            SimulationDataAdministrator.getSimulationData().getFollowedSimulables().add(simulable);
    }

    public static void unfollowSimulable(Simulable simulable){
        SimulationDataAdministrator.getSimulationData().getFollowedSimulables().remove(simulable);
    }

    public static List<Simulable> getFollowedSimulables(){
        return SimulationDataAdministrator.getSimulationData().getFollowedSimulables();
    }

    public static void followRandomOptions() {
        IntStream.range(0,4).boxed().forEach(pos -> followRandomSimulable());
    }

    private static void followRandomSimulable(){
        followSimulable(Simulation.getCompanyListCopy().get(MathUtils.random(0,Simulation.getCompanySize())));
        followSimulable(Simulation.getClientListCopy().get(MathUtils.random(0,Simulation.getClientSize())));
    }
}
