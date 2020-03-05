package backend.implementations.loaders.routine;

import backend.model.client.routine.Routine;
import backend.model.restaurant.Restaurant;
import backend.utils.BillsUtils;
import backend.view.loaders.routine.RoutineChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DistributionRoutineChecker implements RoutineChecker {

    private static final double PERCENTAGE_FOR_RESTAURANT = 0.148;
    private double salary;
    private double budgetForRestaurant;
    private double salarySpent;
    private List<Routine> restaurantRoutines;
    private double provisionalBudgetSpent;

    public DistributionRoutineChecker(double salary, double salarySpent, List<Routine> restaurantRoutines) {
        restartProvisionalBudget();
        this.salary = salary;
        this.salarySpent = salarySpent;
        this.budgetForRestaurant = calculateBudgetForRestaurant(salary);
        this.restaurantRoutines = restaurantRoutines;
    }

    public List<Restaurant> checkRoutines(){
        List<Restaurant> finalList = addIfClientHasBudget(getRestaurants());
        restartProvisionalBudget();
        return finalList;
    }

    private List<Restaurant> getRestaurants() {
        List<Routine> routinesForToday = getRoutines();
        restartRoutines(routinesForToday);
        return routinesForToday.stream()
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
        return addToProvisionalBudget(getBudgetApproximation(restaurant));
    }

    private double getBudgetApproximation(Restaurant restaurant) {
        return restaurant.getPricePlateMean()* BillsUtils.getPlateNumberMean()* BillsUtils.getNumberPeopleMean();
    }

    private boolean addToProvisionalBudget(double money) {
        if(getBudgetAvailable()>provisionalBudgetSpent){
            provisionalBudgetSpent += money;
            return true;
        }
        System.out.println("No budget for the restaurant -> salary: "+ salary + ", budget: " + budgetForRestaurant);
        return false;
    }

    private double getBudgetAvailable() {
        return budgetForRestaurant - salarySpent;
    }


    private double calculateBudgetForRestaurant(double salary) {
        return salary* PERCENTAGE_FOR_RESTAURANT;
    }

    private void restartProvisionalBudget() {
        provisionalBudgetSpent = 0;
    }
}
