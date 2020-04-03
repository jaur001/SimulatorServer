package backend.model.simulables.person.client.routineList.routineListController;

import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.company.restaurant.Restaurant;

import java.util.List;

public interface RoutineCreator {
    Routine create(double salary, List<Restaurant> restaurantList);
}
