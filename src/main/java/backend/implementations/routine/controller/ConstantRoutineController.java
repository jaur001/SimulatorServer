package backend.implementations.routine.controller;

import backend.implementations.routine.creator.RandomRoutineCreator;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulables.person.client.routineList.routineListController.RoutineController;

import java.util.*;
import java.util.stream.IntStream;

public class ConstantRoutineController implements RoutineController {

    public List<Routine> createRoutineList(double salary, List<Restaurant> restaurantList) {
        Integer salaryOption = ClientSettings.getSalaryGroup(salary);
        int restaurantRoutineLengthPerClient = selectNumberOfRestaurants(salary,salaryOption);
        List<Routine> restaurantRoutines = new ArrayList<>();
        IntStream.range(0,restaurantRoutineLengthPerClient)
                .forEach((i) -> addRoutine(salary, restaurantList, restaurantRoutines));
        return restaurantRoutines;
    }

    public void restartRoutine(Routine routine,double salary){
        new RandomRoutineCreator().restartRoutine(routine,salary);
    }

    public void addRoutine(double salary, List<Restaurant> restaurantList, List<Routine> restaurantRoutines) {
        restaurantRoutines.add(new RandomRoutineCreator().create(salary,restaurantList));
    }

    public void deleteRoutine(Routine routine, List<Routine> restaurantRoutines) {
        restaurantRoutines.remove(routine);
    }

    protected int selectNumberOfRestaurants(double salary, Integer salaryOption) {
        return ClientSettings.getNumOfRestaurantSample();
    }



}
