package backend.server.EJB.dataSettings;

import backend.model.simulation.administration.data.SimulationDataController;
import backend.server.EJB.dataSettings.data.*;

import javax.servlet.http.HttpServletRequest;

public class SettingsBuilder {

    public static void build() {
        SimulationDataController.getGeneralSettings().setDefault();
        SimulationDataController.getBillSettings().setDefault();
        SimulationDataController.getClientSettings().setDefault();
        SimulationDataController.getRestaurantSettings().setDefault();
        SimulationDataController.getProviderSettings().setDefault();
        SimulationDataController.getServiceSettings().setDefault();
    }

    public static void build(GeneralData generalData, BillData billData, ClientData clientData, RestaurantData restaurantData, ProviderData providerData, ServiceData serviceData) {
        SimulationDataController.getGeneralSettings().init(generalData);
        SimulationDataController.getBillSettings().init(billData);
        SimulationDataController.getClientSettings().init(clientData);
        SimulationDataController.getRestaurantSettings().init(restaurantData);
        SimulationDataController.getProviderSettings().init(providerData);
        SimulationDataController.getServiceSettings().init(serviceData);
    }


    public static GeneralData getGeneralData() {
        return SimulationDataController.getGeneralSessionData();
    }

    public static BillData getBillData() {
        return SimulationDataController.getBillSessionData();
    }

    public static ClientData getClientData() {
        return SimulationDataController.getClientSessionData();
    }

    public static RestaurantData getRestaurantData() {
        return SimulationDataController.getRestaurantSessionData();
    }

    public static ProviderData getProviderData() {
        return SimulationDataController.getProviderSessionData();
    }

    public static ServiceData getServiceData() {
        return SimulationDataController.getServiceSessionData();
    }

    public static void setCurrentSettingsToSession(HttpServletRequest request) {
        request.getSession(true).setAttribute(GeneralData.class.getSimpleName(), SettingsBuilder.getGeneralData());
        request.getSession(true).setAttribute(BillData.class.getSimpleName(), SettingsBuilder.getBillData());
        request.getSession(true).setAttribute(ClientData.class.getSimpleName(), SettingsBuilder.getClientData());
        request.getSession(true).setAttribute(RestaurantData.class.getSimpleName(), SettingsBuilder.getRestaurantData());
        request.getSession(true).setAttribute(ProviderData.class.getSimpleName(), SettingsBuilder.getProviderData());
        request.getSession(true).setAttribute(ServiceData.class.getSimpleName(), SettingsBuilder.getServiceData());
    }
}
