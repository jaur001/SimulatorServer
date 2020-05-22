package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.GenericSessionDataSettings;
import backend.server.EJB.dataSettings.data.ServiceData;

public class ServiceSessionData extends GenericSessionDataSettings {

    private ServiceData serviceData;

    public ServiceSessionData() {
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof ServiceData) serviceData = (ServiceData) data;
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultServiceData());
    }

    public ServiceData getServiceData() {
        return serviceData;
    }
}
