package backend.view.loaders.database.builder.builders;

import backend.utils.MinMaxData;
import backend.model.simulation.settings.settingsData.data.RestaurantData;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class RestaurantDataBuilder extends Builder<RestaurantData> {
    @Override
    public String getHeader() {
        return RestaurantData.class.getSimpleName();
    }

    @Override
    protected List<Object> getRow(RestaurantData restaurantData) {
        return Arrays.asList("0",restaurantData.getInitialSocialCapital(),restaurantData.getLengthContract().getMin()
                ,restaurantData.getLengthContract().getMax(),restaurantData.getPriceChange()
                ,restaurantData.getCapacity(),restaurantData.getCloseLimit());
    }

    @Override
    protected RestaurantData getItem(Object[] parameters) {
        return new RestaurantData((double)parameters[1],new MinMaxData((int)parameters[2],(int)parameters[3])
                ,(double)parameters[4],(double)parameters[5],(double)parameters[6]);
    }
}
