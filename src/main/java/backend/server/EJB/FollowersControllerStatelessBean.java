package backend.server.EJB;

import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;

import javax.ejb.Stateless;

@Stateless(name = "FollowersControllerStatelessEJB")
public class FollowersControllerStatelessBean {

    public void followSimulable(int NIF){
        SimulationDataController.getTimeLine().getSimulableList().stream()
                .filter(simulable -> simulable.getNIF() == NIF)
                .findFirst().ifPresent(SimulationFollowAdministrator::followSimulable);
    }

    public void unfollowSimulable(int NIF){
        SimulationDataController.getTimeLine().getSimulableList().stream()
                .filter(simulable -> simulable.getNIF() == NIF)
                .findFirst().ifPresent(SimulationFollowAdministrator::unfollowSimulable);
    }

}
