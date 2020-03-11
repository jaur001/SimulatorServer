package backend.implementations.loaders.routine;

import backend.model.client.routine.Counter;
import backend.model.client.routine.Routine;
import backend.model.restaurant.Restaurant;
import backend.model.utils.MathUtils;
import backend.model.utils.RoutineUtils;
import backend.view.loaders.routine.RoutineCreator;

import java.util.List;

public class DistributionRoutineCreator implements RoutineCreator {
    @Override
    public Routine create(double salary, List<Restaurant> restaurantList) {
        int salaryOption = RoutineUtils.getSalaryGroup(salary);
        Restaurant[] restaurants = RoutineUtils.getRestaurantOptions(salary,restaurantList);
         return new Routine(getRandomRestaurant(restaurants), createCounter(salary,salaryOption));
    }

    private Counter createCounter(double salary, double salaryOption){
        return new Counter(selectDays(salary,salaryOption));
    }

    void restartRoutine(Routine routine, double salary){
        routine.setCounter(new Counter(selectDays(salary,RoutineUtils.getSalaryGroup(salary))));
    }

    private int selectDays(double salary, double salaryOption) {
        return RoutineUtils.getNextVisitDaySample(salary,salaryOption);
    }

    private Restaurant getRandomRestaurant(Restaurant[] restaurants) {
        return restaurants[Math.abs(MathUtils.random(0, restaurants.length - 1))];
    }
}
