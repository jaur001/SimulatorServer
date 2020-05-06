package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.server.EJB.dataSettings.data.ClientData;
import backend.server.EJB.dataSettings.GenericDataSettings;
import org.apache.commons.math3.distribution.NormalDistribution;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Stateful(name = "ClientSettingsStatefulEJB")
public class ClientSettingsStatefulBean extends GenericDataSettings {

    private NormalDistribution salaryDistribution;
    private double minSalary;
    private Map<Integer,Integer> restaurantGroup = new HashMap<>();

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof ClientData){
            ClientData clientData = (ClientData) data;
            salaryDistribution = new NormalDistribution(clientData.getSalaryMean(),clientData.getSalarySd());
            minSalary = clientData.getMinSalary();
            restaurantGroup = clientData.getRestaurantGroup();
        }
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultClientSettings());
    }

    public NormalDistribution getSalaryDistribution() {
        return salaryDistribution;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public Map<Integer, Integer> getRestaurantGroup() {
        return restaurantGroup;
    }
}
