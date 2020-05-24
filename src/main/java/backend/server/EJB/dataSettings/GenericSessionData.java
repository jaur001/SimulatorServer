package backend.server.EJB.dataSettings;

import backend.server.EJB.dataSettings.data.ServiceData;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public abstract class GenericSessionData implements Adjustable {

    protected DefaultSettingsSingletonBean defaultSettings;

    public GenericSessionData() {
        initDefaultSettings();
        setDefault();
    }

    protected void initDefaultSettings(){
        try {
            defaultSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/DefaultSettingsSingletonEJB");
        } catch (NamingException e) {
            defaultSettings = new DefaultSettingsSingletonBean();
            defaultSettings.init();
        }
    }
}
