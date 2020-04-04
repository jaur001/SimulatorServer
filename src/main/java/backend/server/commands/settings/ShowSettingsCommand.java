package backend.server.commands.settings;

import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.server.servlets.FrontCommand;

import java.sql.SQLException;

public class ShowSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        Simulation.stop();
        forward("/settings.jsp");
    }
}
