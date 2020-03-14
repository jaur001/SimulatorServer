package backend.model.simulables.client.routineList.routineListController;

import backend.model.simulables.client.Client;
import backend.model.simulables.restaurant.Restaurant;

public interface AmountGenerator {
    double generate(Restaurant restaurant, Client client);
}
