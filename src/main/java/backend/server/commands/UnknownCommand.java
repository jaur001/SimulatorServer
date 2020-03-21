package backend.server.commands;

import backend.server.servlets.FrontCommand;

public class UnknownCommand extends FrontCommand {
    @Override
    public void process() {
        forward("/unknown.jsp");
    }
}
