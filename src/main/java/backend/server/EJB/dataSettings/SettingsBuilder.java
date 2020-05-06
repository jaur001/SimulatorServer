package backend.server.EJB.dataSettings;

import backend.server.EJB.dataSettings.data.*;

public class SettingsBuilder {

    private static SettingsInitializer settingsInitializer;

    public static void initBuilder(SettingsInitializer settingsInitializer){
        SettingsBuilder.settingsInitializer = settingsInitializer;
    }

    public static void build() {
        settingsInitializer.getGeneralDataSettings().setDefault();
        setDefault();
    }

    public static void build(GeneralData generalData) {
        settingsInitializer.getGeneralDataSettings().init(generalData);
        setDefault();
    }

    private static void setDefault() {
        settingsInitializer.getClientDataSettings().setDefault();
        settingsInitializer.getRestaurantDataSettings().setDefault();
        settingsInitializer.getProviderDataSettings().setDefault();
        settingsInitializer.getBillDataSettings().setDefault();
    }

    public static void build(GeneralData generalData, ClientData clientData, ProviderData providerData, BillData billData, RestaurantData restaurantData) {
        setSettings(new SettingsData(generalData,clientData,providerData, billData,restaurantData));
    }

    private static void setSettings(SettingsData data) {
        settingsInitializer.getClientDataSettings().init(data.getClientData());
        settingsInitializer.getProviderDataSettings().init(data.getProviderData());
        settingsInitializer.getBillDataSettings().init(data.getBillData());
        settingsInitializer.getRestaurantDataSettings().init(data.getRestaurantData());
    }
}
