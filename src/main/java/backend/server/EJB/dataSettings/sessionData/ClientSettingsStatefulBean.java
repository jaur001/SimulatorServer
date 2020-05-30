package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.data.ClientData;
import backend.server.EJB.dataSettings.GenericSessionData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "ClientSettingsStatefulEJB")
public class ClientSettingsStatefulBean extends GenericSessionData {

    private ClientData clientData;

    public ClientSettingsStatefulBean() {
        super();
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
