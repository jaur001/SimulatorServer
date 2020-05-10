package backend.server.EJB;

import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.Simulator;

import javax.ejb.Stateless;

@Stateless(name = "FollowersControllerStatelessEJB")
public class FollowersControllerStatelessBean {

    public void followSimulable(int NIF){
        System.out.println(this.getClass().getSimpleName()+ "::followSimulable(" + NIF + ")");
        Simulator.getTimeLine().getSimulableList().stream()
                .filter(simulable -> simulable.getNIF() == NIF)
                .findFirst().ifPresent(Simulation::followSimulable);
    }

    public void unfollowSimulable(int NIF){
        Simulator.getTimeLine().getSimulableList().stream()
                .filter(simulable -> simulable.getNIF() == NIF)
                .findFirst().ifPresent(Simulation::unfollowSimulable);
    }

}
