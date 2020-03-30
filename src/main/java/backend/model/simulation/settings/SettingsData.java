package backend.model.simulation.settings;

import backend.model.simulation.settings.settingsList.*;
import backend.model.simulation.settings.data.*;

public class SettingsData {

    private GeneralData generalData;
    private ClientData clientData;
    private ProviderData providerData;
    private BillData billData;
    private RestaurantData restaurantData;


    public SettingsData(GeneralData generalData) {
        this.generalData = generalData;
        new GeneralSettings().init(this);
        setDefault();
    }

    private void setDefault() {
        new ClientSettings().setDefault();
        new ProviderSettings().setDefault();
        new BillSettings().setDefault();
        new RestaurantSettings().setDefault();
    }

    public SettingsData(GeneralData generalData, ClientData clientData, ProviderData providerData, BillData billData, RestaurantData restaurantData) {
        this.generalData = generalData;
        this.clientData = clientData;
        this.providerData = providerData;
        this.billData = billData;
        this.restaurantData = restaurantData;
        setSettings();
    }

    private void setSettings() {
        new ClientSettings().init(this);
        new ProviderSettings().init(this);
        new BillSettings().init(this);
        new RestaurantSettings().init(this);
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
