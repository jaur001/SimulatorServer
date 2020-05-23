package backend.model.simulation.administration;

import backend.implementations.SQLite.controllers.SQLiteRowDeleter;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.model.bill.generator.XMLBill;
import backend.server.EJB.BillDataSingletonBean;
import backend.utils.DatabaseUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SimulationBillAdministrator {

    private static BillDataSingletonBean billData;

    public static void initBillData() {
        billData = new BillDataSingletonBean();
    }

    public static void addBill(XMLBill bill){
        if(billData.getSize()< DatabaseUtils.getListLimit())billData.addBill(bill);
    }

    public static void resetBills(){
        billData.reset();
        try {
            if(new SQLiteTableSelector().readCount("Bill")!=0)
                new SQLiteRowDeleter().deleteAll("Bill");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database is locked, could not delete Bills of last Simulation");
        }
    }

    public static List<XMLBill> getBillList(int page) {
        int from = Simulation.getFrom(page);
        int to = Simulation.getTo(from,billData.getSize());
        if(billData.getSize()>(page-1)*DatabaseUtils.getPageLength())return billData.getBillList(from, to);
        else return getFromDatabase(page);
    }

    private static List<XMLBill> getFromDatabase(int page) {
        try {
            return new BillBuilder().buildList(new SQLiteTableSelector().read("Bill",page));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }
}
