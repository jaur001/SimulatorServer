package backend.model.simulables.person.client.routineList.routineListController;

import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.restaurant.Restaurant;

public interface AmountGenerator {
    double generate(Restaurant restaurant, Client client);
}
