package backend.server.commands;

import backend.server.frontcontroller.FrontCommand;

public class UnknownCommand extends FrontCommand {
    @Override
    public void process() {
        forward("/unknown.jsp");
    }
}
