package backend.server.commands.tables;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowProvidersCommand extends PageableFrontCommand<Provider> {


    @Override
    public void process() {
        checkPagination();
        forward("/providers.jsp");
    }

    @Override
    protected String getName() {
        return "providerList";
    }

    @Override
    protected List<Provider> loadList() {
        return Simulation.getProviderListCopy();
    }


}
