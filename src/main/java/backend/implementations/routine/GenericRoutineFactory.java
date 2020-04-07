package backend.implementations.routine;

import backend.implementations.routine.strategy.RoutineStrategy;
import backend.model.simulables.person.client.routineList.routine.Counter;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulables.person.client.routineList.routineFactory.RoutineFactory;

import java.util.*;
import java.util.stream.IntStream;

public class GenericRoutineFactory implements RoutineFactory {

    private RoutineStrategy strategy;
    private double salary;

    public GenericRoutineFactory(RoutineStrategy strategy, double salary) {
        this.strategy = strategy;
        this.salary = salary;
    }

    public List<Routine> createRoutineList() {
        Integer salaryOption = ClientSettings.getSalaryGroup(salary);
        int restaurantRoutineLengthPerClient = selectNumberOfRestaurants(salaryOption);
        List<Routine> restaurantRoutines = new ArrayList<>();
        IntStream.range(0,restaurantRoutineLengthPerClient)
                .forEach((i) -> restaurantRoutines.add(create()));
        return restaurantRoutines;
    }

    @Override
    public Routine create() {
        int salaryOption = ClientSettings.getSalaryGroup(salary);
        Restaurant[] restaurants = ClientSettings.getRestaurantOptions(salary,Simulation.getRestaurantList());
        return new Routine(strategy.getRestaurant(restaurants), createCounter(salaryOption));
    }

    private Counter createCounter(double salaryOption){
        return new Counter(selectDays(salaryOption));
    }

    private int selectDays(double salaryOption) {
        return ClientSettings.getNextVisitDaySample(salary,salaryOption);
    }

    public void deleteRoutine(Routine routine, List<Routine> restaurantRoutines) {
        restaurantRoutines.remove(routine);
    }

    private int selectNumberOfRestaurants(Integer salaryOption) {
        return ClientSettings.getNumOfRestaurantSample();
    }



}
