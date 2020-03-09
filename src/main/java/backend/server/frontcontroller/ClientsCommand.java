package backend.server.frontcontroller;

import backend.implementations.loaders.CSV.ClientLoaderCSV;
import backend.model.client.Client;
import java.nio.file.Paths;
import java.util.List;

public class ClientsCommand extends FrontCommand {
    @Override
    public void process() {
        List<Client> clientList = new ClientLoaderCSV().load(context.getRealPath("/CSVFiles/Clients.csv"),3);
        request.setAttribute("clientList",clientList);
        forward("/clients.jsp");
    }
}
