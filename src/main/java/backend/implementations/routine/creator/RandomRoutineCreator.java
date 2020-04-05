package backend.implementations.routine.creator;

import backend.model.simulables.person.client.routineList.routine.Counter;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.utils.MathUtils;
import backend.model.simulables.person.client.routineList.routineListController.RoutineCreator;

import java.util.List;

public class RandomRoutineCreator implements RoutineCreator {
    @Override
    public Routine create(double salary, List<Restaurant> restaurantList) {
        int salaryOption = ClientSettings.getSalaryGroup(salary);
        Restaurant[] restaurants = ClientSettings.getRestaurantOptions(salary,restaurantList);
         return new Routine(getRestaurant(restaurants), createCounter(salary,salaryOption));
    }

    protected Counter createCounter(double salary, double salaryOption){
        return new Counter(selectDays(salary,salaryOption));
    }

    public void restartRoutine(Routine routine, double salary){
        routine.setCounter(new Counter(selectDays(salary,ClientSettings.getSalaryGroup(salary))));
    }

    protected int selectDays(double salary, double salaryOption) {
        return ClientSettings.getNextVisitDaySample(salary,salaryOption);
    }

    protected Restaurant getRestaurant(Restaurant[] restaurants) {
        return restaurants[Math.abs(MathUtils.random(0, restaurants.length - 1))];
    }
}
