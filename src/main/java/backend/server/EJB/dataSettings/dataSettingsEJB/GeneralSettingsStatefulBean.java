package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.server.EJB.dataSettings.data.GeneralData;
import backend.server.EJB.dataSettings.GenericDataSettings;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "GeneralSettingsStatefulEJB")
public class GeneralSettingsStatefulBean extends GenericDataSettings {

    private int clientCount;
    private int restaurantCount;
    private int providerCount;
    private int serviceCount;
    private int workerCount;

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof GeneralData){
            GeneralData generalData = (GeneralData) data;
            clientCount = generalData.getClientCount();
            restaurantCount = generalData.getRestaurantCount();
            providerCount = generalData.getProviderCount();
            serviceCount = generalData.getServiceCount();
            workerCount = generalData.getWorkerCount();
        }
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultGeneralSettings());
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getRestaurantCount() {
        return restaurantCount;
    }

    public int getProviderCount() {
        return providerCount;
    }

    public int getServiceCount() {
        return serviceCount;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public GeneralData getGeneralData() {
        return new GeneralData(clientCount,restaurantCount,providerCount,serviceCount,workerCount);
    }
}
