package backend.server.commands.tables;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
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
    protected List<Provider> getList(int page) {
        return Simulation.getProviderList(page);
    }

    @Override
    protected int getLimit(){
        return Simulation.getProviderSize();
    }

}
