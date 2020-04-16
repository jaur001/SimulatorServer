package backend.model.threads.initializers;

import backend.implementations.loaders.routine.DistributionRoutineController;
import backend.model.client.Client;
import backend.model.client.routine.Routine;
import backend.model.client.routine.RoutineList;
import backend.model.restaurant.Restaurant;
import backend.model.utils.RoutineUtils;

import java.util.List;

public class RoutineThread {

    public static void setClientRoutines(List<Client> clientList, List<Restaurant> restaurants) {
        clientList.parallelStream().forEach(client -> setClientRoutine(client, restaurants));
    }

    private static void setClientRoutine(Client client, List<Restaurant> restaurants) {
        double salary = RoutineUtils.getSalarySample();
        List<Routine> restaurantRoutines = new DistributionRoutineController().createRoutineList(salary,restaurants);
        client.setRoutineList(new RoutineList(salary, restaurantRoutines));
    }

}