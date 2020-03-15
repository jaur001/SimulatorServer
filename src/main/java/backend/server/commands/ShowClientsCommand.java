package backend.server.commands;

import backend.implementations.loaders.CSV.CSVClientLoader;
import backend.model.simulables.client.Client;
import backend.model.simulation.Simulation;
import backend.server.frontcontroller.FrontCommand;

import java.util.List;

public class ShowClientsCommand extends FrontCommand {
    @Override
    public void process() {
        request.setAttribute("clientList", Simulation.getClientList());
        forward("/clients.jsp");
    }
}
