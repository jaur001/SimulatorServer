package backend.model.simulation.settings.settingsData;

import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.settings.settingsData.data.*;

import javax.servlet.http.HttpServletRequest;

public class SettingsBuilder {

    public static void setDefault() {
        SimulationDataController.getGeneralSessionData().setDefault();
        SimulationDataController.getClientSessionData().setDefault();
        SimulationDataController.getRestaurantSessionData().setDefault();
        SimulationDataController.getProviderSessionData().setDefault();
        SimulationDataController.getServiceSessionData().setDefault();
        SimulationDataController.getWorkerSessionData().setDefault();
    }

    public static void build(GeneralData generalData, ClientData clientData, RestaurantData restaurantData,
                             ProviderData providerData, ServiceData serviceData, WorkerData workerData) {
        SimulationDataController.getGeneralSessionData().init(generalData);
        SimulationDataController.getClientSessionData().init(clientData);
        SimulationDataController.getRestaurantSessionData().init(restaurantData);
        SimulationDataController.getProviderSessionData().init(providerData);
        SimulationDataController.getServiceSessionData().init(serviceData);
        SimulationDataController.getWorkerSessionData().init(workerData);
    }


    public static GeneralData getGeneralData() {
        return SimulationDataController.getGeneralData();
    }


    public static ClientData getClientData() {
        return SimulationDataController.getClientData();
    }

    public static RestaurantData getRestaurantData() {
        return SimulationDataController.getRestaurantData();
    }

    public static ProviderData getProviderData() {
        return SimulationDataController.getProviderData();
    }

    public static ServiceData getServiceData() {
        return SimulationDataController.getServiceData();
    }

    public static WorkerData getWorkerData() {
        return SimulationDataController.getWorkerData();
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