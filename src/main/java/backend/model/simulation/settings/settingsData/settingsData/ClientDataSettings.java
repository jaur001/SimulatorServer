package backend.model.simulation.settings.settingsData.settingsData;

import backend.model.simulation.settings.settingsData.data.ClientData;
import backend.model.simulation.settings.settingsData.AdjustableSettingsData;

public class ClientDataSettings extends AdjustableSettingsData {

    private ClientData clientData;

    public ClientDataSettings() {
        super();
    }

    @Override
    public void init(Object data) {
        if(data instanceof ClientData)clientData = (ClientData) data;
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultClientData());
    }

    public ClientData getClientData() {
        return clientData;
    }
}
