package backend.main;

import backend.implementations.SQLite.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.administration.Simulator;

public class Main {
    public static void main(String[] args) {
        Simulator.setUriClient("./out/artifacts/RestaurantSimulator_war_exploded/CSVFiles/Clients.csv");
        Simulator.setUriProvider("./out/artifacts/RestaurantSimulator_war_exploded/CSVFiles/Providers.csv");

        CFDIBillGenerator.setUriSales("./out/artifacts/RestaurantSimulator_war_exploded/xmlFiles/EatingBills/");
        CFDIBillGenerator.setUriPayrolls("./out/artifacts/RestaurantSimulator_war_exploded/xmlFiles/Payrolls/");
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:out/artifacts/RestaurantSimulator_war_exploded/Simulator.db");

        Simulator.startStop(false);

    }
}
