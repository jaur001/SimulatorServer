package backend.server.commands.settings;

import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.server.servlets.FrontCommand;

import java.sql.SQLException;

public class ShowSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        try {
            setToRequest("clientLength", ClientSettings.getLimit());
            setToRequest("restaurantLength", RestaurantSettings.getLimit());
            setToRequest("providerLength", ProviderSettings.getLimit());
        } catch (SQLException | ClassNotFoundException e) {
            setToRequest("clientLength",0);
            setToRequest("restaurantLength",0);
            setToRequest("providerLength",0);
        }
        forward("/settings.jsp");
    }
}
