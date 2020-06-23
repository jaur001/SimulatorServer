package backend.model.simulation.settings.settingsData.settingsData;

import backend.model.simulation.settings.settingsData.AdjustableSettingsData;
import backend.model.simulation.settings.settingsData.data.ProviderData;


public class ProviderDataSettings extends AdjustableSettingsData {

    private ProviderData providerData;

    public ProviderDataSettings() {
        super();
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
