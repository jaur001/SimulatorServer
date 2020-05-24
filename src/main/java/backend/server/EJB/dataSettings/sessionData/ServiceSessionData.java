package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.GenericSessionData;
import backend.server.EJB.dataSettings.data.ServiceData;

public class ServiceSessionData extends GenericSessionData {

    private ServiceData serviceData;

    public ServiceSessionData() {
        super();
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
