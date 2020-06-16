package backend.server.EJB.dataSettings;

public abstract class GenericSessionData implements Adjustable {

    protected static final DefaultSettingsSingletonBean defaultSettings = new DefaultSettingsSingletonBean();

    public GenericSessionData() {
        setDefault();
    }
}
