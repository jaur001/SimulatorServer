package backend.initializers;

import backend.implementations.routine.DistributionRoutineController;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulables.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.ClientSettings;

import java.util.List;

public class RoutineThread {

    public static void setClientRoutines(List<Client> clientList, List<Restaurant> restaurants) {
        clientList.parallelStream().forEach(client -> setClientRoutine(client, restaurants));
    }

    private static void setClientRoutine(Client client, List<Restaurant> restaurants) {
        double salary = ClientSettings.getSalarySample();
        List<Routine> restaurantRoutines = new DistributionRoutineController().createRoutineList(salary,restaurants);
        client.setRoutineList(new RoutineList(salary, restaurantRoutines));
    }

}
