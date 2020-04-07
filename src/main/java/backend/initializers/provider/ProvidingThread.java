package backend.initializers.provider;

import backend.implementations.loaders.providing.RandomProvidingController;
import backend.model.simulables.company.provider.Product;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.Initializer;
import backend.model.simulation.Simulation;

import java.util.stream.IntStream;

public class ProvidingThread{


    public static void initRestaurantsProviders(){
        Simulation.getRestaurantList().parallelStream().forEach(ProvidingThread::initProvidersForRestaurant);
    }


    private static void initProvidersForRestaurant(Restaurant restaurant) {
        IntStream.range(0, Product.values().length).boxed()
                .map(integer -> new RandomProvidingController().provide(Simulation.getProviderList(),Product.values()[integer]))
                .forEach(provider -> {
                    restaurant.addProvider(provider);
                    provider.addRestaurant(restaurant);
                });
    }
}
