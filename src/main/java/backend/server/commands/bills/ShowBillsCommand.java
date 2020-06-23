package backend.server.commands.bills;

import backend.model.bill.generator.XMLBill;
import backend.model.simulation.administration.data.SimulationBillAdministrator;
import backend.server.servlets.PageableFrontCommand;

import java.sql.SQLException;
import java.util.List;

public class ShowBillsCommand extends PageableFrontCommand<XMLBill> {

    @Override
    public void process() {
        checkPagination();
        forward("/bills.jsp");
    }
    protected List<XMLBill> getList(int page) {
        request.getSession(true).setAttribute("billPage",page);
        return SimulationBillAdministrator.getBillPage(page);
    }

    @Override
    protected int getLimit(){
        return SimulationBillAdministrator.getBillCount();
    }

}
