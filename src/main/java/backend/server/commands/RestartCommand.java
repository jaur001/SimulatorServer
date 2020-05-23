package backend.server.commands;

import backend.model.simulation.administration.simulablesControl.SimulatorSwitcher;
import backend.server.servlets.FrontCommand;

public class RestartCommand extends FrontCommand {
    @Override
    public void process() {
        SimulatorSwitcher.restart();
    }
}
