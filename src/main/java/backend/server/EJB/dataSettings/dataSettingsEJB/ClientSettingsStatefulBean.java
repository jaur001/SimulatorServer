package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.server.EJB.dataSettings.MinMaxData;
import backend.server.EJB.dataSettings.data.ClientData;
import backend.server.EJB.dataSettings.GenericDataSettings;
import org.apache.commons.math3.distribution.NormalDistribution;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.Map;

@Stateful(name = "ClientSettingsStatefulEJB")
public class ClientSettingsStatefulBean extends GenericDataSettings {

    private ClientData clientData;

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
        init(defaultSettings.getDefaultClientSettings());
    }

    public NormalDistribution getSalaryDistribution() {
        return new NormalDistribution(clientData.getSalaryMean(), clientData.getSalarySd());
    }

    public double getMinSalary() {
        return clientData.getMinSalary();
    }

    public Map<Integer, Integer> getRestaurantGroup() {
        return clientData.getRestaurantGroup();
    }

    public int getInvitedPeopleMin() {
        return clientData.getInvitedPeopleMin();
    }

    public int getInvitedPeopleMax() {
        return clientData.getInvitedPeopleMax();
    }

    public int getNumOfRestaurantMin() {
        return clientData.getNumOfRestaurantMin();
    }

    public int getNumOfRestaurantMax() {
        return clientData.getNumOfRestaurantMax();
    }

    public MinMaxData getInvitedPeople() {
        return clientData.getInvitedPeople();
    }

    public MinMaxData getNumOfRestaurant() {
        return clientData.getNumOfRestaurant();
    }

    public ClientData getClientData() {
        return clientData;
    }
}
