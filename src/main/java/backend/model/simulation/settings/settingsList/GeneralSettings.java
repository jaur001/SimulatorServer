package backend.model.simulation.settings.settingsList;

import backend.model.simulation.administration.Simulator;
import backend.server.EJB.dataSettings.dataSettingsEJB.GeneralSettingsStatefulBean;

public class GeneralSettings {


    private static GeneralSettingsStatefulBean getGeneralDataSettings() {
        return Simulator.getGeneralDataSettings();
    }

    public static int getClientCount() {
        return getGeneralDataSettings().getClientCount();
    }

    public static int getRestaurantCount() {
        return getGeneralDataSettings().getRestaurantCount();
    }

    public static int getProviderCount() {
        return getGeneralDataSettings().getProviderCount();
    }

    public static int getWorkerCount() {
        return getGeneralDataSettings().getWorkerCount();
    }

    public static int getServiceCount() {
        return getGeneralDataSettings().getServiceCount();
    }
}
