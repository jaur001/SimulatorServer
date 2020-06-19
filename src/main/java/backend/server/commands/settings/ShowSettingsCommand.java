package backend.server.commands.settings;

import backend.model.simulation.administration.centralControl.SimulatorSwitcher;

import backend.server.servlets.FrontCommand;


public class ShowSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        SimulatorSwitcher.stopSimulation();
        forward("/settings.jsp");
    }
}
