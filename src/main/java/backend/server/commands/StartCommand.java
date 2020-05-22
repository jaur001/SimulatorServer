package backend.server.commands;

import backend.implementations.SQLite.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.administration.SimulatorSwitcher;
import backend.server.servlets.FrontCommand;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        SimulatorSwitcher.setUriClient(context.getRealPath("/CSVFiles/Clients.csv"));
        SimulatorSwitcher.setUriProvider(context.getRealPath("/CSVFiles/Providers.csv"));
        CFDIBillGenerator.setUri(context.getRealPath("/xmlFiles")+"/");
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));
        SimulatorSwitcher.startStop(true);
    }
}
