package backend.server.commands.settings;

import backend.server.EJB.dataSettings.SettingsBuilder;
import backend.server.EJB.dataSettings.data.*;
import backend.server.servlets.FrontCommand;


public class SaveSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        SettingsBuilder.build(getGeneralData(),getBillData(),getClientData(),getRestaurantData(),getProviderData());
        forward("/index.jsp");
    }

    private GeneralData getGeneralData() {
        return (GeneralData) request.getSession(true).getAttribute(GeneralData.class.getSimpleName());
    }

    private BillData getBillData() {
        return (BillData) request.getSession(true).getAttribute(BillData.class.getSimpleName());
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
}
