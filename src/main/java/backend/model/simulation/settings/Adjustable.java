package backend.model.simulation.settings;

public interface Adjustable {
    void init(SettingsData data);
    void setDefault();
}
