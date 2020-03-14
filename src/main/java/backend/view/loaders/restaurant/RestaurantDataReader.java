package backend.view.loaders.restaurant;

import backend.model.simulables.restaurant.Restaurant;


public interface RestaurantDataReader {
    Restaurant readData(Object document);
}
