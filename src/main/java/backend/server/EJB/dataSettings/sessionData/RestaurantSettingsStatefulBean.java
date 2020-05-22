package backend.server.EJB.dataSettings.sessionData;

import backend.model.simulables.person.worker.Job;
import backend.server.EJB.dataSettings.GenericSessionDataSettings;
import backend.server.EJB.dataSettings.MinMaxData;
import backend.server.EJB.dataSettings.data.RestaurantData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.Map;

@Stateful(name = "RestaurantSettingsStatefulEJB")
public class RestaurantSettingsStatefulBean extends GenericSessionDataSettings {

    private RestaurantData restaurantData;

    public RestaurantSettingsStatefulBean() {
        initSettings();
    }

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
        init(defaultSettings.getDefaultRestaurantData());
    }
}
