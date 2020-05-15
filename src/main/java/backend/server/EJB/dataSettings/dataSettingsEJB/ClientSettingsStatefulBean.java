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

    private double salaryMean;
    private double salarySd;
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
            salaryMean = clientData.getSalaryMean();
            salarySd = clientData.getSalarySd();
            minSalary = clientData.getMinSalary();
            restaurantGroup = clientData.getRestaurantGroup();
        }
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultClientSettings());
    }

    public NormalDistribution getSalaryDistribution() {
        return new NormalDistribution(salaryMean,salarySd);
    }

    public double getMinSalary() {
        return minSalary;
    }

    public Map<Integer, Integer> getRestaurantGroup() {
        return restaurantGroup;
    }

    public ClientData getClientData() {
        return new ClientData(salaryMean,salarySd,minSalary,restaurantGroup);
    }
}
