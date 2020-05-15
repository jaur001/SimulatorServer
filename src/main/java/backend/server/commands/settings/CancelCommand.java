package backend.server.commands.settings;

import backend.server.EJB.dataSettings.SettingsBuilder;
import backend.server.servlets.FrontCommand;

public class CancelCommand extends FrontCommand {
    @Override
    public void process() {
        SettingsBuilder.setCurrentSettingsToSession(request);
        forward("/index.jsp");
    }

}
