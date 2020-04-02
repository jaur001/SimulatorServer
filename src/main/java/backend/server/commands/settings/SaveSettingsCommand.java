package backend.server.commands.settings;

import backend.model.simulation.settings.SettingsBuilder;
import backend.model.simulation.settings.data.GeneralData;
import backend.model.simulation.settings.settingsList.GeneralSettings;
import backend.server.servlets.FrontCommand;

public class SaveSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        SettingsBuilder.build(getGeneralData());
        forward("/index.jsp");
    }

    private GeneralData getGeneralData() {
        return new GeneralData(getIntParameter("clients"),getIntParameter("restaurants"),getIntParameter("providers"));
    }
}
