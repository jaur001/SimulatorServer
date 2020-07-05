package backend.server.commands.tables;

import backend.model.simulables.person.client.Client;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowClientsCommand extends PageableFrontCommand<Client> {


    @Override
    public void process() {
        checkPagination();
        forward("/clients.jsp");
    }

    @Override
    protected String getName() {
        return "clientList";
    }

    @Override
    protected List<Client> loadList() {
        return Simulation.getClientListCopy();
    }


}
