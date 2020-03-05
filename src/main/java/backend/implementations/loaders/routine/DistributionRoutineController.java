package backend.implementations.loaders.routine;

import backend.model.client.routine.Routine;
import backend.model.restaurant.Restaurant;
import backend.utils.RoutineUtils;
import backend.view.loaders.routine.RoutineController;

import java.util.*;
import java.util.stream.IntStream;

public class DistributionRoutineController implements RoutineController {

    public List<Routine> createRoutineList(double salary, List<Restaurant> restaurantList) {
        Integer salaryOption = RoutineUtils.getSalaryGroup(salary);
        int restaurantRoutineLengthPerClient = selectNumberOfRestaurants(salary,salaryOption);
        List<Routine> restaurantRoutines = new ArrayList<>();
        IntStream.range(0,restaurantRoutineLengthPerClient)
                .forEach((i) -> addRoutine(salary, restaurantList, restaurantRoutines));
        return restaurantRoutines;
    }

    public void addRoutine(double salary, List<Restaurant> restaurantList, List<Routine> restaurantRoutines) {
        restaurantRoutines.add(new DistributionRoutineCreator().create(salary,restaurantList));
    }

    public void restartRoutine(Routine routine,double salary){
        new DistributionRoutineCreator().restartRoutine(routine,salary);
    }

    public void deleteRoutine(Routine routine, List<Routine> restaurantRoutines) {
        restaurantRoutines.remove(routine);
    }

    private int selectNumberOfRestaurants(double salary, Integer salaryOption) {
        return RoutineUtils.getNumOfRestaurantSample();
    }



}
