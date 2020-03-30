package backend.model.simulation.settings;

import backend.model.simulation.settings.settingsList.*;
import backend.model.simulation.settings.data.*;

public class SettingsBuilder {

    public static void build() {
        new GeneralSettings().setDefault();
        setDefault();
    }

    public static void build(GeneralData generalData) {
        new GeneralSettings().init(new SettingsData(generalData));
        setDefault();
    }

    private static void setDefault() {
        new ClientSettings().setDefault();
        new ProviderSettings().setDefault();
        new BillSettings().setDefault();
        new RestaurantSettings().setDefault();
    }

    public static void build(GeneralData generalData, ClientData clientData, ProviderData providerData, BillData billData, RestaurantData restaurantData) {
        setSettings(new SettingsData(generalData,clientData,providerData, billData,restaurantData));
    }

    private static void setSettings(SettingsData data) {
        new ClientSettings().init(data);
        new ProviderSettings().init(data);
        new BillSettings().init(data);
        new RestaurantSettings().init(data);
    }
}
