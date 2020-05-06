package backend.server.EJB.dataSettings;

import backend.model.simulation.settings.settingsList.*;
import backend.server.EJB.dataSettings.dataSettingsEJB.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SettingsInitializer {

    private  GeneralSettingsStatefulBean generalDataSettings;
    private RestaurantSettingsStatefulBean restaurantDataSettings;
    private ClientSettingsStatefulBean clientDataSettings;
    private ProviderSettingsStatefulBean providerDataSettings;
    private BillSettingsStatefulBean billDataSettings;

    public SettingsInitializer(){
        try {
            generalDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/GeneralSettingsStatefulEJB");
            restaurantDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/RestaurantSettingsStatefulEJB");
            clientDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/ClientSettingsStatefulEJB");
            providerDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/ProviderSettingsStatefulEJB");
            billDataSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/BillSettingsStatefulEJB");
        } catch (NamingException e) {
            initGeneralSettings();
            initRestaurantSettings();
            initClientSettings();
            initProviderSettings();
            initBillSettings();
        }
        GeneralSettings.init(generalDataSettings);
        ClientSettings.init(clientDataSettings);
        ProviderSettings.init(providerDataSettings);
        RestaurantSettings.init(restaurantDataSettings);
        BillSettings.init(billDataSettings);
    }

    private void initGeneralSettings() {
        generalDataSettings = new GeneralSettingsStatefulBean();
        generalDataSettings.initSettings();
    }

    private void initClientSettings() {
        clientDataSettings = new ClientSettingsStatefulBean();
        clientDataSettings.initSettings();
    }

    private void initProviderSettings() {
        providerDataSettings = new ProviderSettingsStatefulBean();
        providerDataSettings.initSettings();
    }

    private void initRestaurantSettings() {
        restaurantDataSettings = new RestaurantSettingsStatefulBean();
        restaurantDataSettings.initSettings();
    }

    private void initBillSettings() {
        billDataSettings = new BillSettingsStatefulBean();
        billDataSettings.initSettings();
    }

    public GeneralSettingsStatefulBean getGeneralDataSettings() {
        return generalDataSettings;
    }

    public RestaurantSettingsStatefulBean getRestaurantDataSettings() {
        return restaurantDataSettings;
    }

    public ClientSettingsStatefulBean getClientDataSettings() {
        return clientDataSettings;
    }

    public ProviderSettingsStatefulBean getProviderDataSettings() {
        return providerDataSettings;
    }

    public BillSettingsStatefulBean getBillDataSettings() {
        return billDataSettings;
    }
}
