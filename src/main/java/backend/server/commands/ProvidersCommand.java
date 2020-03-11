package backend.server.commands;

import backend.implementations.loaders.CSV.ClientLoaderCSV;
import backend.implementations.loaders.CSV.ProviderLoaderCSV;
import backend.model.provider.Provider;
import backend.server.frontcontroller.FrontCommand;

import java.util.ArrayList;
import java.util.List;

public class ProvidersCommand extends FrontCommand {

    @Override
    public void process() {
        List<Provider> providerList = new ProviderLoaderCSV().load(context.getRealPath("/CSVFiles/Providers.csv"),3);
        request.setAttribute("providerList",providerList);
        forward("/providers.jsp");
    }
}
