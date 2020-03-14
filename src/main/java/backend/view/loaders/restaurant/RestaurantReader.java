package backend.view.loaders.restaurant;

import backend.model.simulables.restaurant.Restaurant;

import java.io.IOException;
import java.util.List;

public interface RestaurantReader {
    List<Restaurant> read(int pages) throws IOException;
}
