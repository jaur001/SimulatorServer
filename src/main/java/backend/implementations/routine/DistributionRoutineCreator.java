package backend.implementations.routine;

import backend.model.simulables.client.routineList.routine.Counter;
import backend.model.simulables.client.routineList.routine.Routine;
import backend.model.simulables.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.utils.MathUtils;
import backend.model.simulables.client.routineList.routineListController.RoutineCreator;

import java.util.List;

public class DistributionRoutineCreator implements RoutineCreator {
    @Override
    public Routine create(double salary, List<Restaurant> restaurantList) {
        int salaryOption = ClientSettings.getSalaryGroup(salary);
        Restaurant[] restaurants = ClientSettings.getRestaurantOptions(salary,restaurantList);
         return new Routine(getRandomRestaurant(restaurants), createCounter(salary,salaryOption));
    }

    private Counter createCounter(double salary, double salaryOption){
        return new Counter(selectDays(salary,salaryOption));
    }

    void restartRoutine(Routine routine, double salary){
        routine.setCounter(new Counter(selectDays(salary,ClientSettings.getSalaryGroup(salary))));
    }

    private int selectDays(double salary, double salaryOption) {
        return ClientSettings.getNextVisitDaySample(salary,salaryOption);
    }

    private Restaurant getRandomRestaurant(Restaurant[] restaurants) {
        return restaurants[Math.abs(MathUtils.random(0, restaurants.length - 1))];
    }
}
