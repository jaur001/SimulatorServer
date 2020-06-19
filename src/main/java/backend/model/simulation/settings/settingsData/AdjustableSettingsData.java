package backend.model.simulation.settings.settingsData;

public abstract class AdjustableSettingsData {

    protected static final DefaultSettingsSingletonBean defaultSettings = new DefaultSettingsSingletonBean();

    public AdjustableSettingsData() {
        setDefault();
    }

    abstract void init(Object data);
    abstract void setDefault();
}
