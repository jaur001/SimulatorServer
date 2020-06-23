package backend.server.commands.settings;

import backend.model.simulation.settings.settingsData.SettingsBuilder;
import backend.server.servlets.FrontCommand;

public class SetDefaultSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        SettingsBuilder.setDefault();
        forward("/index.jsp");
    }
}
