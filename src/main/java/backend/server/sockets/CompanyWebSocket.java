package backend.server.sockets;
import backend.model.simulables.company.Company;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/companySocketEndpoint")
public class CompanyWebSocket {

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
        simulables.append("<table id='companyTable'>").append("<tr>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>NIF</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Name</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Benefits</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Treasury</td>").append("</tr>");
        SimulationFollowAdministrator.getFollowedSimulables().stream()
                .filter(simulable -> simulable instanceof Company)
                .map(simulable -> (Company) simulable)
                .forEach(company -> appendRow(company,simulables));
        simulables.append("</table>");
        return simulables.toString();
    }

    private void appendRow(Company company, StringBuilder simulables) {
        simulables.append("<tr>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>").append(company.getNIF()).append("</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>").append(company.getName()).append("</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>").append(company.getFinancialData().getBenefits()).append("</td>");
        simulables.append("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>").append(company.getFinancialData().getTreasury()).append("</td>");
        simulables.append("</tr>");
    }

    @OnError
    public void onError(Throwable e){
        System.out.print(ERROR);
    }
}
