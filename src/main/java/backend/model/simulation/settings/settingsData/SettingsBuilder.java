package backend.model.simulation.settings.settingsData;

import backend.model.simulation.administration.data.SimulationDataAdministrator;
import backend.model.simulation.settings.settingsData.data.*;

import javax.servlet.http.HttpServletRequest;

public class SettingsBuilder {

    public static void setDefault() {
        SimulationDataAdministrator.getGeneralSessionData().setDefault();
        SimulationDataAdministrator.getClientSessionData().setDefault();
        SimulationDataAdministrator.getRestaurantSessionData().setDefault();
        SimulationDataAdministrator.getProviderSessionData().setDefault();
        SimulationDataAdministrator.getServiceSessionData().setDefault();
        SimulationDataAdministrator.getWorkerSessionData().setDefault();
    }

    public static void build(GeneralData generalData, ClientData clientData, RestaurantData restaurantData,
                             ProviderData providerData, ServiceData serviceData, WorkerData workerData) {
        SimulationDataAdministrator.getGeneralSessionData().init(generalData);
        SimulationDataAdministrator.getClientSessionData().init(clientData);
        SimulationDataAdministrator.getRestaurantSessionData().init(restaurantData);
        SimulationDataAdministrator.getProviderSessionData().init(providerData);
        SimulationDataAdministrator.getServiceSessionData().init(serviceData);
        SimulationDataAdministrator.getWorkerSessionData().init(workerData);
    }


    public static GeneralData getGeneralData() {
        return SimulationDataAdministrator.getGeneralData();
    }


    public static ClientData getClientData() {
        return SimulationDataAdministrator.getClientData();
    }

    public static RestaurantData getRestaurantData() {
        return SimulationDataAdministrator.getRestaurantData();
    }

    public static ProviderData getProviderData() {
        return SimulationDataAdministrator.getProviderData();
    }

    public static ServiceData getServiceData() {
        return SimulationDataAdministrator.getServiceData();
    }

    public static WorkerData getWorkerData() {
        return SimulationDataAdministrator.getWorkerData();
    }

    public static void setCurrentSettingsToSession(HttpServletRequest request) {
        request.getSession(true).setAttribute(GeneralData.class.getSimpleName(), SettingsBuilder.getGeneralData());
        request.getSession(true).setAttribute(ClientData.class.getSimpleName(), SettingsBuilder.getClientData());
        request.getSession(true).setAttribute(RestaurantData.class.getSimpleName(), SettingsBuilder.getRestaurantData());
        request.getSession(true).setAttribute(ProviderData.class.getSimpleName(), SettingsBuilder.getProviderData());
        request.getSession(true).setAttribute(ServiceData.class.getSimpleName(), SettingsBuilder.getServiceData());
        request.getSession(true).setAttribute(WorkerData.class.getSimpleName(), SettingsBuilder.getWorkerData());
    }
}
