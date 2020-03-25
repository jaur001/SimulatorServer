package backend.server.commands;

import backend.model.simulables.provider.Provider;
import backend.model.simulation.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowProvidersCommand extends PageableFrontCommand<Provider> {

    public static final String TABLE_NAME = "Provider";

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
