package backend.server.commands;

import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;

public class ShowBillsCommand extends FrontCommand {
    @Override
    public void process() {
        request.setAttribute("billList", Simulation.getBillList());
        forward("/bills.jsp");
    }
}
