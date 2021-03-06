package backend.server.commands.simulables;

import backend.model.simulables.Simulable;
import backend.model.simulation.administration.data.SimulationDataAdministrator;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;
import backend.server.servlets.FrontCommand;

public class UnfollowSimulableCommand extends FrontCommand {
    @Override
    public void process() {
        String nif = request.getParameter("NIF");
        int NIF = Integer.parseInt(nif);
        unfollowSimulable(NIF);
    }

    public void unfollowSimulable(int NIF){
        Simulable simulableToQuit = SimulationDataAdministrator.getSimulationData().getFollowedSimulables().stream()
                .filter(simulable -> simulable.getNIF() == NIF)
                .findFirst().orElse(null);
        SimulationFollowAdministrator.unfollowSimulable(simulableToQuit);
    }
}
