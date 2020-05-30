package backend.server.EJB.dataSettings;

import backend.server.EJB.dataSettings.data.ServiceData;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public abstract class GenericSessionData implements Adjustable {

    protected static final DefaultSettingsSingletonBean defaultSettings = new DefaultSettingsSingletonBean();

    public GenericSessionData() {
        setDefault();
    }
}
