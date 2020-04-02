package backend.server.commands;

import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;

public class RestartCommand extends FrontCommand {
    @Override
    public void process() {
        Simulation.restart();
        forward("/index.jsp");
    }
}
