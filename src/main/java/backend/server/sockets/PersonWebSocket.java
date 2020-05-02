package backend.server.sockets;
import backend.model.simulables.person.client.Client;
import backend.model.simulation.administration.Simulation;

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
        simulables.append("<table style= cellspacing='1' bgcolor='#0099cc'>").append("<tr>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>NIF</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Full Name</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Salary</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Job</td>").append("</tr>");
        Simulation.getClientListCopy().forEach(client -> appendRow(client,simulables));
        simulables.append("</table>");
        return simulables.toString();
    }

    private void appendRow(Client client, StringBuilder simulables) {
        simulables.append("<tr>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>").append(client.getNIF()).append("</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>").append(client.getName()).append("</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>").append(client.getSalary()).append("</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>").append(client.getJob()).append("</td>");
        simulables.append("</tr>");
    }

    @OnError
    public void onError(Throwable e){
        System.out.print(ERROR);
    }
}
