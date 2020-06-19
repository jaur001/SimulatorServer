package backend.model.simulation.settings.settingsData.sessionData;

import backend.model.simulation.settings.settingsData.AdjustableSettingsData;
import backend.model.simulation.settings.settingsData.data.RestaurantData;


public class RestaurantDataSettings extends AdjustableSettingsData {

    private RestaurantData restaurantData;

    public RestaurantDataSettings() {
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
