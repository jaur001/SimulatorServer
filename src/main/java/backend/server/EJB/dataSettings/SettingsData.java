package backend.server.EJB.dataSettings;

import backend.model.simulation.settings.settingsList.*;
import backend.server.EJB.dataSettings.data.*;

public class SettingsData {

    private GeneralData generalData;
    private ClientData clientData;
    private ProviderData providerData;
    private BillData billData;
    private RestaurantData restaurantData;


    public SettingsData(GeneralData generalData, ClientData clientData, ProviderData providerData, BillData billData, RestaurantData restaurantData) {
        this.generalData = generalData;
        this.clientData = clientData;
        this.providerData = providerData;
        this.billData = billData;
        this.restaurantData = restaurantData;
    }


    public GeneralData getGeneralData() {
        return generalData;
    }

    public ClientData getClientData() {
        return clientData;
    }

    public ProviderData getProviderData() {
        return providerData;
    }

    public BillData getBillData() {
        return billData;
    }

    public RestaurantData getRestaurantData() {
        return restaurantData;
    }
}
