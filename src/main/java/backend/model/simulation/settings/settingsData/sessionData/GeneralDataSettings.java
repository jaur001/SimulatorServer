package backend.model.simulation.settings.settingsData.sessionData;

import backend.model.simulation.settings.settingsData.data.GeneralData;
import backend.model.simulation.settings.settingsData.AdjustableSettingsData;


public class GeneralDataSettings extends AdjustableSettingsData {

    private GeneralData generalData;

    public GeneralDataSettings() {
        super();
    }

    @Override
    public void init(Object data) {
        if(data instanceof GeneralData)generalData = (GeneralData) data;
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultGeneralData());
    }

    public GeneralData getGeneralData() {
        return generalData;
    }
}
