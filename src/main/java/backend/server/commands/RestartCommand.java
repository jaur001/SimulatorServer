package backend.server.commands;

import backend.model.simulation.simulator.Simulator;
import backend.server.servlets.FrontCommand;

public class RestartCommand extends FrontCommand {
    @Override
    public void process() {
        Simulator.restart();
        forward("/index.jsp");
    }
}
