package backend.server.EJB.dataSettings;

import backend.model.simulation.administration.Simulator;
import backend.server.EJB.dataSettings.data.*;

import javax.servlet.http.HttpServletRequest;

public class SettingsBuilder {

    public static void build() {
        Simulator.getGeneralDataSettings().setDefault();
        Simulator.getClientDataSettings().setDefault();
        Simulator.getRestaurantDataSettings().setDefault();
        Simulator.getProviderDataSettings().setDefault();
        Simulator.getBillDataSettings().setDefault();
    }

    public static void build(GeneralData generalData, BillData billData, ClientData clientData, RestaurantData restaurantData, ProviderData providerData) {
        Simulator.getGeneralDataSettings().init(generalData);
        Simulator.getClientDataSettings().init(clientData);
        Simulator.getProviderDataSettings().init(providerData);
        Simulator.getBillDataSettings().init(billData);
        Simulator.getRestaurantDataSettings().init(restaurantData);
    }


    public static RestaurantData getRestaurantData() {
        return Simulator.getRestaurantDataSettings().getRestaurantData();
    }

    public static BillData getBillData() {
        return Simulator.getBillDataSettings().getBillData();
    }

    public static ProviderData getProviderData() {
        return Simulator.getProviderDataSettings().getProviderData();
    }

    public static ClientData getClientData() {
        return Simulator.getClientDataSettings().getClientData();
    }

    public static GeneralData getGeneralData() {
        return Simulator.getGeneralDataSettings().getGeneralData();
    }

    public static void setCurrentSettingsToSession(HttpServletRequest request) {
        request.getSession(true).setAttribute(GeneralData.class.getSimpleName(), SettingsBuilder.getGeneralData());
        request.getSession(true).setAttribute(ClientData.class.getSimpleName(), SettingsBuilder.getClientData());
        request.getSession(true).setAttribute(RestaurantData.class.getSimpleName(), SettingsBuilder.getRestaurantData());
        request.getSession(true).setAttribute(ProviderData.class.getSimpleName(), SettingsBuilder.getProviderData());
        request.getSession(true).setAttribute(BillData.class.getSimpleName(), SettingsBuilder.getBillData());
    }
}
