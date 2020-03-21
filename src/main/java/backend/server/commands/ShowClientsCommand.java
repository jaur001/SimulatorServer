package backend.server.commands;

import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;

public class ShowClientsCommand extends FrontCommand {
    @Override
    public void process() {
        request.setAttribute("clientList", Simulation.getClientList());
        forward("/clients.jsp");
    }
}
