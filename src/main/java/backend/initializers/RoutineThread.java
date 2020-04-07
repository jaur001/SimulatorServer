package backend.initializers;

import backend.implementations.routine.GenericRoutineFactory;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;

import java.util.List;

public class RoutineThread {

    public static void setClientRoutines() {
        Simulation.getClientList().parallelStream().forEach(RoutineThread::setClientRoutine);
    }

    private static void setClientRoutine(Client client) {
        double salary = ClientSettings.getSalarySample();
        List<Routine> restaurantRoutines = new GenericRoutineFactory(Simulation.ROUTINE_STRATEGY,salary).createRoutineList();
        client.setRoutineList(new RoutineList(salary, restaurantRoutines));
    }

}
