package backend.initializers;

import backend.implementations.routine.GenericRoutineFactory;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulation.Initializer;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;

import java.util.List;

public class RoutineThread{

    public static void setClientRoutines() {
        Simulation.getClientList().forEach(client -> client.setSalary(ClientSettings.getSalarySample()));
        Simulation.getClientList().parallelStream().forEach(RoutineThread::setClientRoutine);
        Simulation.getEmployedWorkers().parallelStream().forEach(RoutineThread::setClientRoutine);
    }

    private static void setClientRoutine(Client client) {
        List<Routine> restaurantRoutines = new GenericRoutineFactory(Simulation.ROUTINE_STRATEGY,client.getSalary()).createRoutineList();
        client.setRoutineList(new RoutineList(client.getSalary(), restaurantRoutines));
    }

}
