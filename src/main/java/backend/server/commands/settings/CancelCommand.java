package backend.server.commands.settings;

import backend.server.servlets.FrontCommand;

public class CancelCommand extends FrontCommand {
    @Override
    public void process() {
        forward("/index.jsp");
    }
}
