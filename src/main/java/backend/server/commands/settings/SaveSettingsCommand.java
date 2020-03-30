package backend.server.commands.settings;

import backend.model.simulation.settings.SettingsBuilder;
import backend.model.simulation.settings.data.GeneralData;
import backend.model.simulation.settings.settingsList.GeneralSettings;
import backend.server.servlets.FrontCommand;

public class SaveSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        SettingsBuilder.build(getGeneralData());
        System.out.println("Settings 1: " + GeneralSettings.getClientCount());
        System.out.println("Settings 2: " + GeneralSettings.getRestaurantCount());
        System.out.println("Settings 3: " + GeneralSettings.getProviderCount());
        forward("/index.jsp");
    }

    private GeneralData getGeneralData() {
        return new GeneralData(getIntParameter("clients"),getIntParameter("restaurants"),getIntParameter("providers"));
    }
}
