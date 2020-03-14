package backend.server.commands;

import backend.implementations.loaders.CSV.CSVClientLoader;
import backend.model.simulables.client.Client;
import backend.server.frontcontroller.FrontCommand;

import java.util.List;

public class ClientsCommand extends FrontCommand {
    @Override
    public void process() {
        List<Client> clientList = new CSVClientLoader(context.getRealPath("/CSVFiles/Clients.csv")).load(3);
        request.setAttribute("clientList",clientList);
        forward("/clients.jsp");
    }
}
