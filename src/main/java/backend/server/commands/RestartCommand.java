package backend.server.commands;

import backend.model.simulation.administration.Simulator;
import backend.server.servlets.FrontCommand;

public class RestartCommand extends FrontCommand {
    @Override
    public void process() {
        Simulator.restart();
    }
}
