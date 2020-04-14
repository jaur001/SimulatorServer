package backend.model.simulables.person.client.routineList;

import backend.implementations.routine.GenericRoutineChecker;
import backend.implementations.routine.GenericRoutineFactory;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;

import java.util.List;

public class RoutineList {
    private double salary;
    private double budget;
    private final double budgetLimit;
    private List<Routine> restaurantRoutines;

    public RoutineList(double salary,List<Routine> restaurantRoutines) {
        budgetLimit = salary * ClientSettings.PERCENTAGE_FOR_RESTAURANT;
        budget = budgetLimit;
        this.salary = salary;
        this.restaurantRoutines = restaurantRoutines;
    }

    public List<Restaurant> checkRoutines(){
        if(isEmpty()) restaurantRoutines = new GenericRoutineFactory(Simulation.ROUTINE_STRATEGY,salary).createRoutineList();
        return new GenericRoutineChecker(salary, budget,restaurantRoutines, Simulation.ROUTINE_STRATEGY).checkRoutines();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBudget() {
        return budget;
    }

    public void restartBudget(){
        budget = budgetLimit;
    }

    public void increaseBudget(double amount) {
        budget += Math.min(amount,budgetLimit);
    }

    public void decreaseBudget(double amount) {
        budget -= amount;
    }

    public void printCount() {
        restaurantRoutines.forEach((routine -> System.out.print(routine.getRestaurant().getCompanyName() + ": " + routine.getCount()+", ")));
        System.out.print("\n");
    }

    public boolean isEmpty() {
        return restaurantRoutines.size()==0;
    }
}
