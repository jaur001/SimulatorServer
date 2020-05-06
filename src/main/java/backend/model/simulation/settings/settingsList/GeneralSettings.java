package backend.model.simulation.settings.settingsList;

import backend.server.EJB.dataSettings.dataSettingsEJB.GeneralSettingsStatefulBean;

public class GeneralSettings {

    private static GeneralSettingsStatefulBean generalDataSettings;

    public static int getClientCount() {
        return generalDataSettings.getClientCount();
    }

    public static int getRestaurantCount() {
        return generalDataSettings.getRestaurantCount();
    }

    public static int getProviderCount() {
        return generalDataSettings.getProviderCount();
    }

    public static int getWorkerCount() {
        return generalDataSettings.getWorkerCount();
    }

    public static int getServiceCount() {
        return generalDataSettings.getServiceCount();
    }

    public static void init(GeneralSettingsStatefulBean dataSettings) {
        generalDataSettings = dataSettings;
    }
}
