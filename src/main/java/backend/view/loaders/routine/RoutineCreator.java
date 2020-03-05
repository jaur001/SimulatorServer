package backend.view.loaders.routine;

import backend.model.client.routine.Routine;
import backend.model.restaurant.Restaurant;

import java.util.List;

public interface RoutineCreator {

    Routine create(double salary, List<Restaurant> restaurantList);
}
