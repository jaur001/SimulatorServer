package backend.model.simulation.administration.data;

import backend.model.simulation.timeLine.TimeLine;
import backend.server.EJB.dataSettings.data.*;
import backend.server.EJB.dataSettings.sessionData.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationDataController {

    private static SimulationData sessionData;

    public static void initSessionData() {
        sessionData = new SimulationData();
    }

    public static SimulationData getSessionData(){
        return sessionData;
    }

    public static TimeLine getTimeLine() {
        return getSessionData().getTimeLine();
    }

    public static GeneralData getGeneralSessionData() {
        return getGeneralSettings().getGeneralData();
    }

    public static BillData getBillSessionData() {
        return getBillSettings().getBillData();
    }

    public static ClientData getClientSessionData() {
        return getClientSettings().getClientData();
    }

    public static RestaurantData getRestaurantSessionData() {
        return getRestaurantSettings().getRestaurantData();
    }

    public static ProviderData getProviderSessionData() {
        return getProviderSettings().getProviderData();
    }

    public static ServiceData getServiceSessionData() {
        return getServiceSettings().getServiceData();
    }

    public static GeneralSettingsStatefulBean getGeneralSettings() {
        return getSessionData().getGeneralDataSettings();
    }

    public static BillSettingsStatefulBean getBillSettings() {
        return getSessionData().getBillDataSettings();
    }

    public static ClientSettingsStatefulBean getClientSettings() {
        return getSessionData().getClientDataSettings();
    }

    public static RestaurantSettingsStatefulBean getRestaurantSettings() {
        return getSessionData().getRestaurantDataSettings();
    }

    public static ProviderSettingsStatefulBean getProviderSettings() {
        return getSessionData().getProviderDataSettings();
    }

    public static ServiceSessionData getServiceSettings() {
        return getSessionData().getServiceSessionData();
    }

    public static AtomicBoolean getRestart() {
        return getSessionData().getRestart();
    }

    public static AtomicBoolean getExecuting(){
        return getSessionData().getExecuting();
    }
}
