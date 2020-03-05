package backend.view.loaders.routine;

import backend.model.client.Client;
import backend.model.restaurant.Restaurant;

import java.util.List;

public interface RoutineLoader {
    List<Client> load(List<Restaurant> restaurantList, List<Client> clientList);
}
