package backend.server.commands.settings;

import backend.model.simulation.simulator.Simulator;

import backend.server.servlets.FrontCommand;


public class ShowSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        Simulator.stopSimulation();
        forward("/settings.jsp");
    }
}
