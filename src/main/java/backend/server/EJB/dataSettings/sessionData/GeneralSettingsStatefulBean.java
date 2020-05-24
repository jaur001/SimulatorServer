package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.data.GeneralData;
import backend.server.EJB.dataSettings.GenericSessionData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "GeneralSettingsStatefulEJB")
public class GeneralSettingsStatefulBean extends GenericSessionData {

    private GeneralData generalData;

    public GeneralSettingsStatefulBean() {
        initSettings();
    }

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
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
