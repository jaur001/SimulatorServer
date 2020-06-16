package backend.server.commands.simulables;

import backend.model.simulation.administration.data.SimulationDataController;
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
        SimulationDataController.getTimeLine().getSimulableList().stream()
                .filter(simulable -> simulable.getNIF() == NIF)
                .findFirst().ifPresent(SimulationFollowAdministrator::unfollowSimulable);
    }
}
