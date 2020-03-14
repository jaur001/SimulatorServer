package backend.model.simulables.client.routineList.routineListController;

import backend.model.simulables.client.routineList.routine.Routine;
import backend.model.simulables.restaurant.Restaurant;

import java.util.List;

public interface RoutineController {
    List<Routine> createRoutineList(double salary, List<Restaurant> restaurantList);
    void addRoutine(double salary, List<Restaurant> restaurantList, List<Routine> restaurantRoutines);
    void deleteRoutine(Routine routine, List<Routine> restaurantRoutines);
}
