package backend.implementations.routine.checker;

import backend.implementations.routine.controller.ConstantRoutineController;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.BillSettings;
import backend.model.simulables.person.client.routineList.routineListController.RoutineChecker;
import backend.model.simulation.settings.settingsList.ClientSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConstantRoutineChecker implements RoutineChecker {

    protected double salary;
    protected List<Routine> restaurantRoutines;
    protected double provisionalBudget;

    public ConstantRoutineChecker(double salary, double budget, List<Routine> restaurantRoutines) {
        this.provisionalBudget = budget;
        this.salary = salary;
        this.restaurantRoutines = restaurantRoutines;
    }

    public List<Restaurant> checkRoutines(){
        return addIfClientHasBudget(getRestaurants());
    }

    protected List<Restaurant> getRestaurants() {
        List<Routine> routinesForToday = getRoutines();
        restartRoutines(routinesForToday);
        return routinesForToday.stream()
                .limit(3)
                .map(Routine::getRestaurant)
                .collect(Collectors.toList());
    }

    protected List<Routine> getRoutines() {
        return restaurantRoutines.stream()
                .filter(Routine::check)
                .collect(Collectors.toList());
    }

    protected void restartRoutines(List<Routine> routines) {
        routines.forEach(routine -> new ConstantRoutineController().restartRoutine(routine,salary));
    }

    protected List<Restaurant> addIfClientHasBudget(List<Restaurant> restaurantOptions) {
        return restaurantOptions.stream()
                .filter(this::thereIsEnoughBudget)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    protected boolean thereIsEnoughBudget(Restaurant restaurant) {
        double budgetApproximation = getBudgetApproximation(restaurant);
        if(provisionalBudget >= budgetApproximation) {
            provisionalBudget -= budgetApproximation;
            return true;
        }
        System.out.println("No budget for the restaurant -> salary: "+ salary + ", budget: " + salary* ClientSettings.PERCENTAGE_FOR_RESTAURANT);
        return false;
    }

    protected double getBudgetApproximation(Restaurant restaurant) {
        return restaurant.getPricePlateMean()* BillSettings.getPlateNumberMean() * ClientSettings.getPeopleInvitedMean();
    }

}
