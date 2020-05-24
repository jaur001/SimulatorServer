package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.GenericSessionData;
import backend.server.EJB.dataSettings.data.ProviderData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "ProviderSettingsStatefulEJB")
public class ProviderSettingsStatefulBean extends GenericSessionData {

    private ProviderData providerData;

    public ProviderSettingsStatefulBean() {
        initSettings();
    }

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof ProviderData) providerData = (ProviderData) data;
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultProviderData());
    }

    public ProviderData getProviderData() {
        return providerData;
    }
}
