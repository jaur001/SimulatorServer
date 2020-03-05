package backend.view.loaders.routine;

import backend.model.client.Client;
import backend.model.restaurant.Restaurant;

public interface AmountGenerator {
    double generate(Restaurant restaurant, Client client);
}
