package backend.main;

import backend.implementations.SQLite.connector.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.administration.centralControl.SimulationBillAdministrator;
import backend.model.simulation.administration.initializer.SimulatorSwitcher;

public class Main {
    public static void main(String[] args) {
        SimulatorSwitcher.setUriClient("./out/artifacts/RestaurantSimulator_war_exploded/CSVFiles/Clients.csv");
        SimulatorSwitcher.setUriProvider("./out/artifacts/RestaurantSimulator_war_exploded/CSVFiles/Providers.csv");
        CFDIBillGenerator.setUri("./out/artifacts/RestaurantSimulator_war_exploded/xmlFiles/");
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:out/artifacts/RestaurantSimulator_war_exploded/Simulator.db");
        SimulationBillAdministrator.resetBills();
        SimulatorSwitcher.startStop(false);

    }
}
