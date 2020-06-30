package backend.model.simulation.settings.settingsList;

import backend.model.simulation.administration.data.SimulationDataAdministrator;
import backend.model.simulation.settings.settingsData.data.GeneralData;

public class GeneralSettings {


    private static GeneralData getGeneralDataSettings() {
        return SimulationDataAdministrator.getGeneralData();
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
