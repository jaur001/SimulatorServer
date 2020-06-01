package backend.model.simulation.administration.centralControl;

import backend.implementations.SQLite.SQLiteTableAdministrator;
import backend.implementations.SQLite.controllers.SQLiteRowDeleter;
import backend.implementations.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.model.NIFCreator.BillNIFCreator;
import backend.model.bill.generator.XMLBill;
import backend.model.simulation.administration.SimulatorThreadPool;
import backend.server.EJB.BillDataSingletonBean;
import backend.view.loaders.database.DatabaseManager;
import backend.view.loaders.database.TableAdministrator;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SimulationBillAdministrator {

    private static final BillDataSingletonBean billData = new BillDataSingletonBean();
    private static final TableAdministrator administrator = new SQLiteTableAdministrator();

    public static void addBill(XMLBill bill){
        if(billData.getSize()< DatabaseManager.getListLimit())billData.addBill(bill);
        //saveInDatabase(bill);
    }

    public static void resetBills(){
        billData.reset();
        try {
            if(getBillCount() !=0)
                administrator.deleteAll(XMLBill.class);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database is locked, could not delete Bills of last Simulation");
        }
        BillNIFCreator.initInitialValue();
        DatabaseManager.setBillInitialPrimaryKeyValue();
    }

    public static List<XMLBill> getBillPage(int page) {
        int from = DatabaseManager.getFrom(page);
        int to = DatabaseManager.getTo(from,billData.getSize());
        if(billData.getSize()>(page-1)* DatabaseManager.getPageLength())return billData.getBillList(from, to);
        else return getFromDatabase(page);
    }

    private static List<XMLBill> getFromDatabase(int page) {
        try {
            return administrator.readPage(page,XMLBill.class);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    public static void saveInDatabase(XMLBill bill) {
        SimulatorThreadPool.getExecutor().submit(() -> {
            try {
                administrator.save(bill);
            } catch (SQLException | ClassNotFoundException ignored) {
            }
        });
    }

    public static int getBillCount() throws SQLException, ClassNotFoundException {
        return administrator.readCount(XMLBill.class);
    }
}
