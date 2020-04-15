package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulation.settings.Adjustable;
import backend.model.simulation.settings.SettingsData;

public class GeneralSettings implements Adjustable {

    private static final double PERCENTAGE_PROVIDERS = 0.9;
    private static int clientCount;
    private static int restaurantCount;
    private static int providerCount;
    private static int serviceCount;
    private static int workerCount;

    static {
        getDefaultSettings();
    }

    private static void getDefaultSettings() {
        clientCount = 10;
        restaurantCount = 1;
        providerCount = (int)Math.max(restaurantCount* PERCENTAGE_PROVIDERS,Product.values().length);
        serviceCount = Math.max(restaurantCount/5, Service.values().length);
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

    public static int getServiceCount() {
        return serviceCount;
    }
}
