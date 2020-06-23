package backend.model.simulation.settings.settingsData.settingsData;

import backend.model.simulation.settings.settingsData.AdjustableSettingsData;
import backend.model.simulation.settings.settingsData.data.ServiceData;

public class ServiceSettingsData extends AdjustableSettingsData {

    private ServiceData serviceData;

    public ServiceSettingsData() {
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
