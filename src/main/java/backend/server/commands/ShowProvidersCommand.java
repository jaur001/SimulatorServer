package backend.server.commands;

import backend.implementations.loaders.CSV.CSVProviderLoader;
import backend.model.simulables.provider.Provider;
import backend.model.simulation.Simulation;
import backend.server.frontcontroller.FrontCommand;

import java.util.List;

public class ShowProvidersCommand extends FrontCommand {

    @Override
    public void process() {
        request.setAttribute("providerList", Simulation.getProviderList());
        forward("/providers.jsp");
    }
}
