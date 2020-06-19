package backend.server.commands.settings;

import backend.model.simulation.settings.settingsData.SettingsBuilder;
import backend.model.simulation.settings.settingsData.data.*;
import backend.server.servlets.FrontCommand;


public class SaveSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        SettingsBuilder.build(getGeneralData(),getClientData(),getRestaurantData(),getProviderData(),getServiceData(),getWorkerData());
        forward("/index.jsp");
    }

    private GeneralData getGeneralData() {
        return (GeneralData) request.getSession(true).getAttribute(GeneralData.class.getSimpleName());
    }


    private ClientData getClientData() {
        return (ClientData) request.getSession(true).getAttribute(ClientData.class.getSimpleName());
    }

    private RestaurantData getRestaurantData() {
        return (RestaurantData) request.getSession(true).getAttribute(RestaurantData.class.getSimpleName());
    }

    private ProviderData getProviderData() {
        return (ProviderData) request.getSession(true).getAttribute(ProviderData.class.getSimpleName());
    }

    private ServiceData getServiceData() {
        return (ServiceData) request.getSession(true).getAttribute(ServiceData.class.getSimpleName());
    }

    private WorkerData getWorkerData() {
        return (WorkerData) request.getSession(true).getAttribute(WorkerData.class.getSimpleName());
    }


}
