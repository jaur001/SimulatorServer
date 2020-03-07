package backend.server.frontcontroller;

import backend.model.client.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientsCommand extends FrontCommand {
    @Override
    public void process() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client("Paco"));
        clientList.add(new Client("Ronnie"));
        clientList.add(new Client("Augusto"));
        request.setAttribute("clientList",clientList);
        forward("/clients.jsp");
    }
}
