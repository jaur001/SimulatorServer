package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.server.EJB.dataSettings.MinMaxData;
import backend.server.EJB.dataSettings.data.ClientData;
import backend.server.EJB.dataSettings.GenericDataSettings;
import org.apache.commons.math3.distribution.NormalDistribution;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Stateful(name = "ClientSettingsStatefulEJB")
public class ClientSettingsStatefulBean extends GenericDataSettings {

    private ClientData data;

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof ClientData){
            this.data = (ClientData) data;

        }
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultClientSettings());
    }

    public NormalDistribution getSalaryDistribution() {
        return new NormalDistribution(data.getSalaryMean(),data.getSalarySd());
    }

    public double getMinSalary() {
        return data.getMinSalary();
    }

    public Map<Integer, Integer> getRestaurantGroup() {
        return data.getRestaurantGroup();
    }

    public int getInvitedPeopleMin() {
        return data.getInvitedPeopleMin();
    }

    public int getInvitedPeopleMax() {
        return data.getInvitedPeopleMax();
    }

    public int getNumOfRestaurantMin() {
        return data.getNumOfRestaurantMin();
    }

    public int getNumOfRestaurantMax() {
        return data.getNumOfRestaurantMax();
    }

    public MinMaxData getInvitedPeople() {
        return data.getInvitedPeople();
    }

    public MinMaxData getNumOfRestaurant() {
        return data.getNumOfRestaurant();
    }

    public ClientData getClientData() {
        return data;
    }
}
