package backend.model.simulables.person.client.routineList.routineFactory;

import backend.model.simulables.person.client.routineList.routine.Routine;

import java.util.List;

public interface RoutineFactory {
    List<Routine> createRoutineList();
    Routine create();
    void deleteRoutine(Routine routine, List<Routine> restaurantRoutines);
}
