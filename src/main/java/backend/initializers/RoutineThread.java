package backend.initializers;

import backend.implementations.routine.GenericRoutineFactory;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulation.administration.Simulation;

import java.util.List;

public class RoutineThread{

    public static void setClientRoutines() {
        Simulation.getClientListCopy().parallelStream().forEach(RoutineThread::setClientRoutine);
        Simulation.getEmployedWorkers().parallelStream().forEach(RoutineThread::setClientRoutine);
    }

    private static void setClientRoutine(Client client) {
        List<Routine> restaurantRoutines = new GenericRoutineFactory(Simulation.ROUTINE_STRATEGY,client.getSalary()).createRoutineList();
        client.setRoutineList(new RoutineList(client.getSalary(), restaurantRoutines));
    }

}
