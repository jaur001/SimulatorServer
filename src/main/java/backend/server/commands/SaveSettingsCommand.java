package backend.server.commands;

import backend.server.servlets.FrontCommand;

public class SaveSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        forward("/index.jsp");
    }
}
