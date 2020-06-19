package backend.server.commands.settings;

import backend.model.simulation.settings.settingsData.SettingsBuilder;
import backend.server.servlets.FrontCommand;

public class CancelCommand extends FrontCommand {
    @Override
    public void process() {
        SettingsBuilder.setCurrentSettingsToSession(request);
        forward("/index.jsp");
    }

}
