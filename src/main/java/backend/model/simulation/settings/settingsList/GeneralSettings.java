package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.provider.Product;
import backend.model.simulation.settings.Adjustable;
import backend.model.simulation.settings.SettingsData;

public class GeneralSettings implements Adjustable {

    private static int clientCount;
    private static int restaurantCount;
    private static int providerCount;
    private static int workerCount;

    static {
        getDefaultSettings();
    }

    private static void getDefaultSettings() {
        clientCount = 10;
        restaurantCount = 1;
        providerCount = restaurantCount*Product.values().length;
        workerCount = restaurantCount*100;
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

    public static int getWorkerCount() {
        return workerCount;
    }
}
