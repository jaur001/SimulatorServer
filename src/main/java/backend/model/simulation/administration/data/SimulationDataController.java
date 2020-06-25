package backend.model.simulation.administration.data;

import backend.model.simulation.timeLine.TimeLine;
import backend.model.simulation.settings.settingsData.data.*;
import backend.model.simulation.settings.settingsData.settingsData.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationDataController {

    private static SimulationData simulationData;

    public static void initSimulationData() {
        simulationData = new SimulationData();
    }

    public static SimulationData getSimulationData(){
        return simulationData;
    }

    public static TimeLine getTimeLine() {
        return getSimulationData().getTimeLine();
    }

    public static GeneralData getGeneralData() {
        return getGeneralSessionData().getGeneralData();
    }

    public static ClientData getClientData() {
        return getClientSessionData().getClientData();
    }

    public static RestaurantData getRestaurantData() {
        return getRestaurantSessionData().getRestaurantData();
    }

    public static ProviderData getProviderData() {
        return getProviderSessionData().getProviderData();
    }

    public static ServiceData getServiceData() {
        return getServiceSessionData().getServiceData();
    }

    public static WorkerData getWorkerData(){
        return getWorkerSessionData().getWorkerData();
    }

    public static GeneralDataSettings getGeneralSessionData() {
        return getSimulationData().getGeneralDataSettings();
    }

    public static ClientDataSettings getClientSessionData() {
        return getSimulationData().getClientDataSettings();
    }

    public static RestaurantDataSettings getRestaurantSessionData() {
        return getSimulationData().getRestaurantDataSettings();
    }

    public static ProviderDataSettings getProviderSessionData() {
        return getSimulationData().getProviderDataSettings();
    }

    public static ServiceSettingsData getServiceSessionData() {
        return getSimulationData().getServiceSettingsData();
    }

    public static WorkerSettingsData getWorkerSessionData() {
        return getSimulationData().getWorkerSettingsData();
    }

    public static AtomicBoolean getRestart() {
        return getSimulationData().getRestart();
    }

    public static AtomicBoolean getExecuting(){
        return getSimulationData().getExecuting();
    }
}
