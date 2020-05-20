package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.model.simulables.person.worker.Job;
import backend.server.EJB.dataSettings.GenericDataSettings;
import backend.server.EJB.dataSettings.MinMaxData;
import backend.server.EJB.dataSettings.data.RestaurantData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.LinkedHashMap;
import java.util.Map;

@Stateful(name = "RestaurantSettingsStatefulEJB")
public class RestaurantSettingsStatefulBean extends GenericDataSettings {

    private RestaurantData restaurantData;

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof RestaurantData)restaurantData = (RestaurantData) data;
    }

    public RestaurantData getRestaurantData() {
        return restaurantData;
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultRestaurantSettings());
    }

    public Map<Job, Integer> getWorkerSalaryTable() {
        return restaurantData.getWorkerSalaryTable();
    }

    public double getInitialSocialCapital() {
        return restaurantData.getInitialSocialCapital();
    }

    public double getPriceChange() {
        return restaurantData.getPriceChange();
    }

    public double getCapacity() {
        return restaurantData.getCapacity();
    }

    public int getCloseLimit() {
        return restaurantData.getCloseLimit();
    }

    public MinMaxData getLengthContract() {
        return restaurantData.getLengthContract();
    }
}
