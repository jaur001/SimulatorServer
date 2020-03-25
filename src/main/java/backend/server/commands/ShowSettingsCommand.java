package backend.server.commands;

import backend.server.servlets.FrontCommand;

public class ShowSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        forward("/settings.jsp");
    }
}
