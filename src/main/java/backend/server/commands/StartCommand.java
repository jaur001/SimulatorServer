package backend.server.commands;

import backend.model.simulation.administration.centralControl.SimulatorSwitcher;
import backend.server.servlets.FrontCommand;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        SimulatorSwitcher.startStop(true);
    }
}
