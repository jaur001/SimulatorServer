package backend.implementations.routine;

import backend.implementations.routine.strategy.RoutineStrategy;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.routineFactory.RoutineFactory;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.model.simulation.settings.settingsList.BillSettings;
import backend.model.simulables.person.client.routineList.routineFactory.RoutineChecker;
import backend.model.simulation.settings.settingsList.ClientSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenericRoutineChecker implements RoutineChecker {

    private double salary;
    private List<Routine> restaurantRoutines;
    private double provisionalBudget;
    private RoutineStrategy strategy;

    public GenericRoutineChecker(double salary, double provisionalBudget, List<Routine> restaurantRoutines, RoutineStrategy strategy) {
        this.salary = salary;
        this.restaurantRoutines = restaurantRoutines;
        this.provisionalBudget = provisionalBudget;
        this.strategy = strategy;
    }

    public List<Restaurant> checkRoutines(){
        return addIfClientHasBudget(getRestaurants());
    }

    private List<Restaurant> getRestaurants() {
        List<Routine> routinesForToday = getRoutines();
        restartRoutines(routinesForToday);
        return routinesForToday.stream()
                .filter(this::isNotClosed)
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
        RoutineFactory routineFactory = new GenericRoutineFactory(strategy, salary);
        routines.forEach(routine -> {
            Routine newRoutine = routineFactory.create();
            if(newRoutine!=null) routine = newRoutine;
            else restaurantRoutines.remove(routine);
        });
    }

    private boolean isNotClosed(Routine routine) {
        return Simulation.getRestaurantListCopy().contains(routine.getRestaurant());
    }

    private List<Restaurant> addIfClientHasBudget(List<Restaurant> restaurantOptions) {
        if(restaurantOptions.size()==0)return new ArrayList<>();
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
        return false;
    }

    private double getBudgetApproximation(Restaurant restaurant) {
        return restaurant.getPricePlateMean()* BillSettings.getPlateNumberMean() * ClientSettings.getPeopleInvitedMean();
    }

}
