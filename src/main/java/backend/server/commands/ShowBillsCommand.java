package backend.server.commands;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.bill.generator.XMLBill;
import backend.model.simulation.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.sql.SQLException;
import java.util.List;

public class ShowBillsCommand extends PageableFrontCommand<XMLBill> {

    public static final String TABLE_NAME = "Bill";

    @Override
    public void process() {
        checkPagination();
        forward("/bills.jsp");
    }
    protected List<XMLBill> getList(int page) {
        Simulation.setBillPage(page);
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
