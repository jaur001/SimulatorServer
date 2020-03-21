package backend.server.commands;

import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;

public class ShowProvidersCommand extends FrontCommand {

    @Override
    public void process() {
        request.setAttribute("providerList", Simulation.getProviderList());
        forward("/providers.jsp");
    }
}
