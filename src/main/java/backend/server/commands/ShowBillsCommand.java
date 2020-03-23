package backend.server.commands;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.bill.generator.XMLBill;
import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;
import backend.utils.DatabaseUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.List;

public class ShowBillsCommand extends FrontCommand {
    @Override
    public void process() {
        setPage();
        setMaxPage();
        forward("/bills.jsp");
    }

    private void setPage() {
        String attribute = request.getParameter("page");
        if(attribute == null){
            request.setAttribute("page","1");
            Simulation.setActualPage(1);
            request.setAttribute("billList", Simulation.getBillList(1));
        }
        else{
            request.setAttribute("page",attribute);
            getBills(Integer.parseInt(attribute));
        }
    }

    private void getBills(int page) {
        Simulation.setActualPage(page);
        List<XMLBill> billList = Simulation.getBillList(page);
        if(billList.size() == 0) forward("/wait.jsp");
        request.setAttribute("billList", billList);
    }



    private void setMaxPage() {
        try {
            int count = new SQLiteTableSelector().readCount("Bill");
            request.setAttribute("length", getMaxPage(count) +"");
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("length","1");
        }
    }

    private int getMaxPage(int count) {
        int maxPage = count / DatabaseUtils.getPageLength();
        return count%DatabaseUtils.getPageLength()==0?maxPage:maxPage+1;
    }
}
