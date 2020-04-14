package backend.initializers.provider;

import backend.implementations.providing.RandomProvidingController;
import backend.model.simulables.company.provider.Product;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.administration.Simulation;

import java.util.Objects;
import java.util.stream.IntStream;

public class ProvidingThread{


    public static void initRestaurantsProviders(){
        Simulation.getRestaurantListCopy().parallelStream().forEach(ProvidingThread::initProvidersForRestaurant);
    }


    public static void initProvidersForRestaurant(Restaurant restaurant) {
        IntStream.range(0, Product.values().length).boxed()
                .map(integer -> new RandomProvidingController().provide(Product.values()[integer]))
                .filter(Objects::nonNull)
                .forEach(provider -> {
                    restaurant.getAdministrator().addProvider(provider);
                    provider.addClient(restaurant);
                });
    }
}
