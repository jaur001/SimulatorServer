package backend.server.commands;

import backend.implementations.SQLite.SQLiteDatabaseConnector;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulation.administration.Simulator;
import backend.server.servlets.FrontCommand;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        Simulator.setUriClient(context.getRealPath("/CSVFiles/Clients.csv"));
        Simulator.setUriProvider(context.getRealPath("/CSVFiles/Providers.csv"));
        CFDIBillGenerator.setUri(context.getRealPath("/xmlFiles")+"/");
        SQLiteDatabaseConnector.setUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));
        Simulator.startStop(true);
        setToRequest("simulables", getSimulables());
    }

    private String getSimulables() {
        StringBuilder simulables = new StringBuilder();
        Simulator.getTimeLine().getSimulableList()
                .forEach(simulable -> simulables.append(appendSimulable(simulable)).append("!!\n"));
        return simulables.toString();
    }

    public String appendSimulable(Simulable simulable) {
        return String.join(",",simulable.getSimulable());
    }
}
