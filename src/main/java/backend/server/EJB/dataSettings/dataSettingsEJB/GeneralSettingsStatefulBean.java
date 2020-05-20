package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.server.EJB.dataSettings.data.GeneralData;
import backend.server.EJB.dataSettings.GenericDataSettings;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "GeneralSettingsStatefulEJB")
public class GeneralSettingsStatefulBean extends GenericDataSettings {

    private GeneralData generalData;

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof GeneralData)generalData = (GeneralData) data;
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultGeneralSettings());
    }

    public int getClientCount() {
        return generalData.getClientCount();
    }

    public int getRestaurantCount() {
        return generalData.getRestaurantCount();
    }

    public int getProviderCount() {
        return generalData.getProviderCount();
    }

    public int getServiceCount() {
        return generalData.getServiceCount();
    }

    public int getWorkerCount() {
        return generalData.getWorkerCount();
    }

    public GeneralData getGeneralData() {
        return generalData;
    }
}
