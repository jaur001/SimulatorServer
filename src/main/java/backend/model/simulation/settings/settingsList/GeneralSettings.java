package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.provider.Product;
import backend.model.simulation.settings.Adjustable;
import backend.model.simulation.settings.SettingsData;

public class GeneralSettings implements Adjustable {

    private static final double PERCENTAGE_PROVIDERS = 0.9;
    private static int clientCount;
    private static int restaurantCount;
    private static int providerCount;
    private static int workerCount;

    static {
        getDefaultSettings();
    }

    private static void getDefaultSettings() {
        clientCount = 10000;
        restaurantCount = 100;
        providerCount = (int)Math.max(restaurantCount* PERCENTAGE_PROVIDERS,Product.values().length);
        workerCount = restaurantCount*40;
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
