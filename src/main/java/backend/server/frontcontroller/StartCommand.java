package backend.server.frontcontroller;

import backend.implementations.loaders.CSV.ClientLoaderCSV;

import backend.model.client.Client;

import java.util.ArrayList;
import java.util.List;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        List<Client> clientList = new ClientLoaderCSV().load("C:/Users/PROPIETARIO/Desktop/HPDS/SimulatorServer/CSVFiles",3);
        //List<Client> clientList = new ArrayList<>();
        //clientList.add(new Client("Paco"));
        //clientList.add(new Client("Ronnie"));
        //clientList.add(new Client("Augusto"));
        request.setAttribute("clientList",clientList);
        forward("/clients.jsp");
    }
}
