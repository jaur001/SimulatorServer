package backend.view.loaders.routine;

import backend.model.client.routine.Routine;
import backend.model.restaurant.Restaurant;

import java.util.List;

public interface RoutineController {
    List<Routine> createRoutineList(double salary, List<Restaurant> restaurantList);
    void addRoutine(double salary, List<Restaurant> restaurantList, List<Routine> restaurantRoutines);
    void deleteRoutine(Routine routine, List<Routine> restaurantRoutines);
}
