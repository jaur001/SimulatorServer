package backend.server.EJB.dataSettings;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public abstract class GenericDataSettings implements Adjustable {
    protected DefaultSettingsSingletonBean defaultSettings;
    protected void initDefaultSettings(){
        try {
            defaultSettings = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/DefaultSettingsSingletonEJB");
        } catch (NamingException e) {
            defaultSettings = new DefaultSettingsSingletonBean();
            defaultSettings.init();
        }
    }
}
