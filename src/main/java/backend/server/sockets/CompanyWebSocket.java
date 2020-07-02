package backend.server.sockets;
import backend.model.simulables.company.Company;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;
import backend.utils.EuroFormatter;

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
        System.out.println("Company socket opened....");
    }

    @OnClose
    public void onClose(){
        System.out.println("Company socket close....");
    }

    @OnMessage
    public String onMessage(String message){
        StringBuilder simulables = new StringBuilder();
        simulables.append("<table id='companyTable'>").append("<tr>");
        simulables.append("<th>NIF</th>");
        simulables.append("<th>Name</th>");
        simulables.append("<th>Benefits</th>");
        simulables.append("<th>Total Active</th>");
        simulables.append("<th>Total Passive</th>");
        simulables.append("<th>Treasury</th>").append("</tr>");
        SimulationFollowAdministrator.getFollowedSimulables().stream()
                .filter(simulable -> simulable instanceof Company)
                .map(simulable -> (Company) simulable)
                .forEach(company -> appendRow(company,simulables));
        simulables.append("</table>");
        return simulables.toString();
    }

    private void appendRow(Company company, StringBuilder simulables) {
        simulables.append("<tr>");
        simulables.append("<td>").append(company.getNIF()).append("</td>");
        simulables.append("<td>").append(company.getName()).append("</td>");
        if(Simulation.getCompanyListCopy().contains(company))
            simulables.append("<td>").append(EuroFormatter.formatEuro(company.getFinancialData().getLastMonthBenefits())).append("</td>");
        else simulables.append("<td>").append("Closed").append("</td>");
        simulables.append("<td>").append(EuroFormatter.formatEuro(company.getFinancialData().getTotalActive())).append("</td>");
        simulables.append("<td>").append(EuroFormatter.formatEuro(company.getFinancialData().getTotalPassive())).append("</td>");
        simulables.append("<td>").append(EuroFormatter.formatEuro(company.getFinancialData().getTreasury())).append("</td>");
        simulables.append("</tr>");
    }

    @OnError
    public void onError(Throwable e){
        System.out.print(ERROR);
    }
}
