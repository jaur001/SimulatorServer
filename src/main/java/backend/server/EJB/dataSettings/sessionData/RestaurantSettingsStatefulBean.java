package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.GenericSessionData;
import backend.server.EJB.dataSettings.data.RestaurantData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "RestaurantSettingsStatefulEJB")
public class RestaurantSettingsStatefulBean extends GenericSessionData {

    private RestaurantData restaurantData;

    public RestaurantSettingsStatefulBean() {
        super();
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
