package backend.server.commands;

import backend.implementations.database.SQLite.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        Simulation.setUriClient(context.getRealPath("/CSVFiles/Clients.csv"));
        Simulation.setUriProvider(context.getRealPath("/CSVFiles/Providers.csv"));

        CFDIBillGenerator.setUriSales(context.getRealPath("/xmlFiles/EatingBills")+"/");
        CFDIBillGenerator.setUriPayrolls(context.getRealPath("/xmlFiles/Payrolls")+"/");
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));

        if(!Simulation.isInitialized()) Simulation.execute();
        else Simulation.changeExecuting();
        forward("/index.jsp");
    }
}
