package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.MinMaxData;
import backend.server.EJB.dataSettings.data.ClientData;
import backend.server.EJB.dataSettings.GenericSessionDataSettings;
import org.apache.commons.math3.distribution.NormalDistribution;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.Map;

@Stateful(name = "ClientSettingsStatefulEJB")
public class ClientSettingsStatefulBean extends GenericSessionDataSettings {

    private ClientData clientData;

    public ClientSettingsStatefulBean() {
        initSettings();
    }

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
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
