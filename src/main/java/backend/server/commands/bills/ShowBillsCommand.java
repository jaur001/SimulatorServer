package backend.server.commands.bills;

import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.model.bill.generator.XMLBill;
import backend.model.simulation.administration.Simulation;
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
        return Simulation.getBillList(page);
    }

    @Override
    protected int getLimit(){
        try {
            return new SQLiteTableSelector().readCount("Bill");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
