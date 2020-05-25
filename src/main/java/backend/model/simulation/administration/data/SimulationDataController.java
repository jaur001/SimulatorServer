package backend.model.simulation.administration.data;

import backend.model.simulation.timeLine.TimeLine;
import backend.server.EJB.dataSettings.data.*;
import backend.server.EJB.dataSettings.sessionData.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationDataController {

    private static SimulationData simulationData;

    public static void initSessionData() {
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

    public static GeneralSettingsStatefulBean getGeneralSessionData() {
        return getSimulationData().getGeneralDataSettings();
    }

    public static ClientSettingsStatefulBean getClientSessionData() {
        return getSimulationData().getClientDataSettings();
    }

    public static RestaurantSettingsStatefulBean getRestaurantSessionData() {
        return getSimulationData().getRestaurantDataSettings();
    }

    public static ProviderSettingsStatefulBean getProviderSessionData() {
        return getSimulationData().getProviderDataSettings();
    }

    public static ServiceSessionData getServiceSessionData() {
        return getSimulationData().getServiceSessionData();
    }

    public static WorkerSessionData getWorkerSessionData() {
        return getSimulationData().getWorkerSessionData();
    }

    public static AtomicBoolean getRestart() {
        return getSimulationData().getRestart();
    }

    public static AtomicBoolean getExecuting(){
        return getSimulationData().getExecuting();
    }
}
