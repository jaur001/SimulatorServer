package backend.model.simulation.administration.data;

import backend.implementations.SQLite.SQLiteTableAdministrator;
import backend.model.NIFCreator.BillNIFCreator;
import backend.model.bill.generator.XMLBill;
import backend.model.simulation.administration.SimulatorThreadPool;
import backend.view.loaders.database.DatabaseManager;
import backend.view.loaders.database.TableAdministrator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SimulationBillAdministrator {

    private static SimulationBillData simulationBillData = new SimulationBillData();
    private static final TableAdministrator administrator = new SQLiteTableAdministrator();

    public static void addBill(XMLBill bill){
        simulationBillData.addBill(bill);
        //saveInDatabase(bill);
    }

    public static void resetBills(){
        simulationBillData.reset();
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
        int to = DatabaseManager.getTo(from, simulationBillData.getSize());
        return simulationBillData.getBillList(from, to);
        //return getFromDatabase(page);
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
        SimulatorThreadPool.executeTask(() -> {
            try {
                administrator.save(bill);
            } catch (SQLException | ClassNotFoundException ignored) {
            }
        });
    }

    public static int getBillCount(){
        return simulationBillData.getSize();
    }
}
