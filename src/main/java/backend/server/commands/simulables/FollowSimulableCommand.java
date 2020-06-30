package backend.server.commands.simulables;

import backend.model.simulation.administration.data.SimulationDataAdministrator;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;
import backend.server.servlets.FrontCommand;

public class FollowSimulableCommand extends FrontCommand {

    @Override
    public void process() {
        int NIF = Integer.parseInt(request.getParameter("NIF"));
        followSimulable(NIF);
    }

    public void followSimulable(int NIF){
        SimulationDataAdministrator.getTimeLine().getSimulableList().stream()
                .filter(simulable -> simulable.getNIF() == NIF)
                .findFirst().ifPresent(SimulationFollowAdministrator::followSimulable);
    }
}
