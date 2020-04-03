package backend.implementations.routine;

import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.BillSettings;
import backend.model.simulables.person.client.routineList.routineListController.RoutineChecker;
import backend.model.simulation.settings.settingsList.ClientSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DistributionRoutineChecker implements RoutineChecker {

    private double salary;
    private List<Routine> restaurantRoutines;
    private double provisionalBudget;

    public DistributionRoutineChecker(double salary, double budget, List<Routine> restaurantRoutines) {
        this.provisionalBudget = budget;
        this.salary = salary;
        this.restaurantRoutines = restaurantRoutines;
    }

    public List<Restaurant> checkRoutines(){
        return addIfClientHasBudget(getRestaurants());
    }

    private List<Restaurant> getRestaurants() {
        List<Routine> routinesForToday = getRoutines();
        restartRoutines(routinesForToday);
        return routinesForToday.stream()
                .limit(3)
                .map(Routine::getRestaurant)
                .collect(Collectors.toList());
    }

    private List<Routine> getRoutines() {
        return restaurantRoutines.stream()
                .filter(Routine::check)
                .collect(Collectors.toList());
    }

    private void restartRoutines(List<Routine> routines) {
        routines.forEach(routine -> new DistributionRoutineController().restartRoutine(routine,salary));
    }

    private List<Restaurant> addIfClientHasBudget(List<Restaurant> restaurantOptions) {
        return restaurantOptions.stream()
                .filter(this::thereIsEnoughBudget)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    private boolean thereIsEnoughBudget(Restaurant restaurant) {
        double budgetApproximation = getBudgetApproximation(restaurant);
        if(provisionalBudget >= budgetApproximation) {
            provisionalBudget -= budgetApproximation;
            return true;
        }
        System.out.println("No budget for the restaurant -> salary: "+ salary + ", budget: " + salary* ClientSettings.PERCENTAGE_FOR_RESTAURANT);
        return false;
    }

    private double getBudgetApproximation(Restaurant restaurant) {
        return restaurant.getPricePlateMean()* BillSettings.getPlateNumberMean() * ClientSettings.getPeopleInvitedMean();
    }

}
