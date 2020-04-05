package backend.implementations.routine.checker;

import backend.implementations.routine.controller.ConstantRoutineController;
import backend.model.simulables.person.client.routineList.routine.Routine;

import java.util.List;

public class ChangingRoutineChecker extends ConstantRoutineChecker {


    public ChangingRoutineChecker(double salary, double budget, List<Routine> restaurantRoutines) {
        super(salary, budget, restaurantRoutines);
    }

    @Override
    protected void restartRoutines(List<Routine> routines) {
        routines.forEach(routine -> new ConstantRoutineController().restartRoutine(routine,salary));
    }
}
