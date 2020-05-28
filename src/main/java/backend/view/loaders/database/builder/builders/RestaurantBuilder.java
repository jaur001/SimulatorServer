package backend.view.loaders.database.builder.builders;

import backend.model.simulables.company.restaurant.PriceRange;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class RestaurantBuilder extends Builder<Restaurant> {

    @Override
    public String getHeader() {
        return "Restaurant";
    }

    @Override
    protected List<Object> getRow(Restaurant restaurant){
        return Arrays.asList(new Object[]{restaurant.getNIF()
                ,restaurant.getName(),restaurant.getTelephoneNumber()
                ,restaurant.getStreet(),restaurant.getMinPricePlate()
                ,restaurant.getMaxPricePlate(),restaurant.getTables()});
    }

    @Override
    protected Restaurant getItem(Object[] parameters) {
        return new Restaurant((int)parameters[0],(String)parameters[1],(String)parameters[2],(String)parameters[3],new PriceRange((int)parameters[4],(int)parameters[5]),(int)parameters[6]);
    }
}
