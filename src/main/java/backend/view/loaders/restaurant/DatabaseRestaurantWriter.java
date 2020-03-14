package backend.view.loaders.restaurant;

import backend.model.simulables.restaurant.Restaurant;

import java.util.List;

public interface DatabaseRestaurantWriter {
    void write(List<Restaurant> restaurantList);
}
