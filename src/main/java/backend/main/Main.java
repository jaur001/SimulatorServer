package backend.main;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.Simulation;

public class Main {
    public static void main(String[] args) {
        Simulation.setUriClient("./CSVFiles/Clients.csv");
        Simulation.setUriProvider("./CSVFiles/Providers.csv");

        CFDIBillGenerator.setUriSales("./xmlFiles/EatingBills/");
        CFDIBillGenerator.setUriPayrolls("./xmlFiles/Payrolls/");
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:out/artifacts/RestaurantSimulator_war_exploded/Simulator.db");

        Simulation.execute();

    }
}
