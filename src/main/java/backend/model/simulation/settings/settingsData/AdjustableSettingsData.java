package backend.model.simulation.settings.settingsData;

public abstract class AdjustableSettingsData {

    protected static final DefaultSettingsSingletonBean defaultSettings = new DefaultSettingsSingletonBean();

    public AdjustableSettingsData() {
        setDefault();
    }
    public abstract void init(Object data);
    public abstract void setDefault();
}
