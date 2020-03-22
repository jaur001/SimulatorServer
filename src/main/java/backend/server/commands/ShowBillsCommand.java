package backend.server.commands;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;
import backend.utils.DatabaseUtils;

import java.sql.SQLException;

public class ShowBillsCommand extends FrontCommand {
    @Override
    public void process() {
        setPage();
        setLength();
        forward("/bills.jsp");
    }

    private void setPage() {
        String attribute = (String) request.getAttribute("page");
        if(attribute == null){
            request.setAttribute("page","1");
            request.setAttribute("billList", Simulation.getBillList());
        }
        else{
            request.setAttribute("page",attribute);
            getBills(attribute);
        }
    }

    private void getBills(String attribute) {
        try {
            new SQLiteTableSelector().read("Bill",Integer.parseInt(attribute));
        } catch (SQLException e) {
            forward("/wait.jsp");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setLength() {
        try {
            int count = new SQLiteTableSelector().readCount("Bill");
            System.out.println(count);
            request.setAttribute("length",(count/DatabaseUtils.getPageLength())+"");
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("length","0");
        }
    }
}
