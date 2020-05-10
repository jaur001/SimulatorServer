package backend.server.EJB.dataSettings;

import backend.model.simulation.administration.Simulator;
import backend.server.EJB.dataSettings.data.*;

public class SettingsBuilder {

    public static void build() {
        Simulator.getGeneralDataSettings().setDefault();
        setDefault();
    }

    public static void build(GeneralData generalData) {
        Simulator.getGeneralDataSettings().init(generalData);
        setDefault();
    }

    private static void setDefault() {
        Simulator.getClientDataSettings().setDefault();
        Simulator.getRestaurantDataSettings().setDefault();
        Simulator.getProviderDataSettings().setDefault();
        Simulator.getBillDataSettings().setDefault();
    }

    public static void build(GeneralData generalData, ClientData clientData, ProviderData providerData, BillData billData, RestaurantData restaurantData) {
        setSettings(new SettingsData(generalData,clientData,providerData, billData,restaurantData));
    }

    private static void setSettings(SettingsData data) {
        Simulator.getClientDataSettings().init(data.getClientData());
        Simulator.getProviderDataSettings().init(data.getProviderData());
        Simulator.getBillDataSettings().init(data.getBillData());
        Simulator.getRestaurantDataSettings().init(data.getRestaurantData());
    }
}
