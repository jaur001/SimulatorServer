package backend.model.simulation.settings.settingsList;

import backend.model.simulables.provider.Product;
import backend.model.simulation.settings.Settings;
import backend.model.simulation.settings.SettingsData;

public class GeneralSettings implements Settings {

    private static int clientCount;
    private static int restaurantCount;
    private static int providerCount;

    static {
        getDefaultSettings();
    }

    private static void getDefaultSettings() {
        clientCount = 1000;
        restaurantCount = 10;
        providerCount = restaurantCount*Product.values().length;
    }

    @Override
    public void init(SettingsData data) {
        clientCount = data.getGeneralData().getClientCount();
        restaurantCount = data.getGeneralData().getRestaurantCount();
        providerCount = data.getGeneralData().getProviderCount();
    }

    @Override
    public void setDefault() {
        getDefaultSettings();
    }

    public static int getClientCount() {
        return clientCount;
    }

    public static int getRestaurantCount() {
        return restaurantCount;
    }

    public static int getProviderCount() {
        return providerCount;
    }
}
