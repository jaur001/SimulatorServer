package backend.server.commands;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.Simulator;
import backend.server.servlets.FrontCommand;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        Simulator.setUriClient(context.getRealPath("/CSVFiles/Clients.csv"));
        Simulator.setUriProvider(context.getRealPath("/CSVFiles/Providers.csv"));

        CFDIBillGenerator.setUriSales(context.getRealPath("/xmlFiles/EatingBills")+"/");
        CFDIBillGenerator.setUriPayrolls(context.getRealPath("/xmlFiles/Payrolls")+"/");
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));
        Simulator.startStop(true);
        setToRequest("isRunning",Simulator.isRunning());
        forward("/index.jsp");
    }
}
