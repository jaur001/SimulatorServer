package backend.main;

import backend.implementations.SQLite.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.administration.Simulator;

public class Main {
    public static void main(String[] args) {
        Simulator.setUriClient("./CSVFiles/Clients.csv");
        Simulator.setUriProvider("./CSVFiles/Providers.csv");

        CFDIBillGenerator.setUriSales("./xmlFiles/EatingBills/");
        CFDIBillGenerator.setUriPayrolls("./xmlFiles/Payrolls/");
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:out/artifacts/RestaurantSimulator_war_exploded/Simulator.db");

        Simulator.startStop(false);

    }
}
