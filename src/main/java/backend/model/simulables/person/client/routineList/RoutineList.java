package backend.model.simulables.person.client.routineList;

import backend.implementations.routine.DistributionRoutineChecker;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.restaurant.Restaurant;

import java.util.List;

public class RoutineList {
    private double salary;
    private double salarySpent;
    private List<Routine> restaurantRoutines;

    public RoutineList(double salary,List<Routine> restaurantRoutines) {
        restartBudget();
        this.salary = salary;
        this.restaurantRoutines = restaurantRoutines;
    }

    public List<Restaurant> checkRoutines(){
        return new DistributionRoutineChecker(salary,salarySpent,restaurantRoutines).checkRoutines();
    }

    public double getSalary() {
        return salary;
    }

    public double getSalarySpent() {
        return salarySpent;
    }

    public List<Routine> getClientRoutines() {
        return restaurantRoutines;
    }

    public void setClientRoutines(List<Routine> restaurantRoutines) {
        this.restaurantRoutines = restaurantRoutines;
    }

    public void restartBudget(){
        salarySpent = 0;
    }

    public void decreaseBudget(double amount) {
        salarySpent += amount;
    }

    public void printCount() {
        restaurantRoutines.forEach((routine -> System.out.print(routine.getRestaurant().getName() + ": " + routine.getCount()+", ")));
        System.out.print("\n");
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
