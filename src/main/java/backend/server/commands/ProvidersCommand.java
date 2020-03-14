package backend.server.commands;

import backend.implementations.loaders.CSV.CSVProviderLoader;
import backend.model.simulables.provider.Provider;
import backend.server.frontcontroller.FrontCommand;

import java.util.List;

public class ProvidersCommand extends FrontCommand {

    @Override
    public void process() {
        List<Provider> providerList = new CSVProviderLoader(context.getRealPath("/CSVFiles/Providers.csv")).load(3);
        request.setAttribute("providerList",providerList);
        forward("/providers.jsp");
    }
}
