package backend.view.loaders.restaurant;

import backend.model.restaurant.Restaurant;


public interface RestaurantDataReader {
    Restaurant readData(Object document);
}
