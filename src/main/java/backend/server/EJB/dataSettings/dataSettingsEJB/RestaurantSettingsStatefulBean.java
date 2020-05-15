package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.model.simulables.person.worker.Job;
import backend.server.EJB.dataSettings.GenericDataSettings;
import backend.server.EJB.dataSettings.data.RestaurantData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.LinkedHashMap;
import java.util.Map;

@Stateful(name = "RestaurantSettingsStatefulEJB")
public class RestaurantSettingsStatefulBean extends GenericDataSettings {

    private double initialSocialCapital;
    private Map<Job, Integer> workerSalaryTable = new LinkedHashMap<>();

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof RestaurantData){
            RestaurantData restaurantData = (RestaurantData) data;
            initialSocialCapital = restaurantData.getInitialSocialCapital();
            workerSalaryTable = restaurantData.getWorkerSalaryTable();
        }
    }

    public RestaurantData getRestaurantData() {
        return new RestaurantData(initialSocialCapital,workerSalaryTable);
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultRestaurantSettings());
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public Map<Job, Integer> getWorkerSalaryTable() {
        return workerSalaryTable;
    }
}
