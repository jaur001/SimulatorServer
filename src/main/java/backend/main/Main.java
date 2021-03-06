package backend.main;

import backend.implementations.SQLite.connector.DatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.administration.data.SimulationBillAdministrator;
import backend.model.simulation.administration.data.SimulationDataAdministrator;
import backend.model.simulation.administration.centralControl.SimulatorSwitcher;

public class Main {
    public static void main(String[] args) {
        SimulatorSwitcher.setUriClient("./out/artifacts/RestaurantSimulator_war_exploded/CSVFiles/Clients.csv");
        SimulatorSwitcher.setUriProvider("./out/artifacts/RestaurantSimulator_war_exploded/CSVFiles/Providers.csv");
        SimulatorSwitcher.setUriEvents("./out/artifacts/RestaurantSimulator_war_exploded/Events/event-log.txt");
        CFDIBillGenerator.setUri("./out/artifacts/RestaurantSimulator_war_exploded/xmlFiles/");
        DatabaseConnector.setUri("jdbc:sqlite:out/artifacts/RestaurantSimulator_war_exploded/Simulator.db");
        SimulationBillAdministrator.resetBills();
        SimulationDataAdministrator.initSimulationData();
        SimulatorSwitcher.startStop(false);

    }
}
