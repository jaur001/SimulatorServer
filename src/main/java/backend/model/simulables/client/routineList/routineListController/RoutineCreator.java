package backend.model.simulables.client.routineList.routineListController;

import backend.model.simulables.client.routineList.routine.Routine;
import backend.model.simulables.restaurant.Restaurant;

import java.util.List;

public interface RoutineCreator {
    Routine create(double salary, List<Restaurant> restaurantList);
}
