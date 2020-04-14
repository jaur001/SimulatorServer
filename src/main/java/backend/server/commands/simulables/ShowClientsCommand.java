package backend.server.commands.simulables;

import backend.model.simulables.person.client.Client;
import backend.model.simulation.administration.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowClientsCommand extends PageableFrontCommand<Client> {

    public static final String TABLE_NAME = "Client";

    @Override
    public void process() {
        checkPagination();
        forward("/clients.jsp");
    }

    @Override
    protected List<Client> getList(int page) {
        return Simulation.getClientList(page);
    }

    @Override
    protected int getLimit() {
        return Simulation.getClientSize();
    }

}
