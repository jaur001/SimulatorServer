package backend.implementations.routine.controller;

import backend.implementations.routine.creator.BestRoutineCreator;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.routine.Routine;

import java.util.List;

public class ChangingRoutineController extends ConstantRoutineController {

    @Override
    public void restartRoutine(Routine routine,double salary){
        new BestRoutineCreator().restartRoutine(routine,salary);
    }

    @Override
    public void addRoutine(double salary, List<Restaurant> restaurantList, List<Routine> restaurantRoutines) {
        restaurantRoutines.add(new BestRoutineCreator().create(salary,restaurantList));
    }
}
