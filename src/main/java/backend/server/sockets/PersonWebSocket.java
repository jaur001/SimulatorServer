package backend.server.sockets;
import backend.model.simulables.person.client.Client;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/personSocketEndpoint")
public class PersonWebSocket {

    private static final String ERROR = "";

    @OnOpen
    public void onOpen(){
        System.out.println("Event socket opened....");
    }

    @OnClose
    public void onClose(){
        System.out.println("Event socket close....");
    }

    @OnMessage
    public String onMessage(String message){
        StringBuilder simulables = new StringBuilder();
        simulables.append("<table id='personTable'>").append("<tr>");
        simulables.append("<th>NIF</th>");
        simulables.append("<th>Full Name</th>");
        simulables.append("<th>Age</th>");
        simulables.append("<th>Salary</th>");
        simulables.append("<th>Salary Spent</th>");
        simulables.append("<th>Job</th>").append("</tr>");
        SimulationFollowAdministrator.getFollowedSimulables().stream()
                .filter(simulable -> simulable instanceof Client)
                .map(simulable -> (Client) simulable)
                .forEach(client -> appendRow(client,simulables));
        simulables.append("</table>");
        return simulables.toString();
    }

    private void appendRow(Client client, StringBuilder simulables) {
        simulables.append("<tr>");
        simulables.append("<td>").append(client.getNIF()).append("</td>");
        simulables.append("<td>").append(client.getName()).append("</td>");
        simulables.append("<td>").append(client.getAge()).append("</td>");
        simulables.append("<td>").append(client.getSalaryToShow()).append("</td>");
        simulables.append("<td>").append(client.getSalarySpent()).append("</td>");
        simulables.append("<td>").append(client.getJob()).append("</td>");
        simulables.append("</tr>");
    }

    @OnError
    public void onError(Throwable e){
        System.out.print(ERROR);
    }
}
