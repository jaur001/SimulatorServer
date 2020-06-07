package backend.initializers.secondaryCompanies.provider;

import backend.implementations.secondaryCompanies.providing.RandomProvidingInitializer;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.provider.Product;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.administration.centralControl.Simulation;

import java.util.Objects;
import java.util.stream.IntStream;

public class ProvidingThread{


    public static void initRestaurantsProviders(){
        Simulation.getRestaurantListCopy().parallelStream().forEach(ProvidingThread::initProvidersForRestaurant);
    }


    public static void initProvidersForRestaurant(Restaurant restaurant) {
        IntStream.range(0, Product.values().length).boxed()
                .map(integer -> new RandomProvidingInitializer().provide(Product.values()[integer]))
                .filter(Objects::nonNull)
                .forEach(provider -> {
                    restaurant.addProvider(provider);
                    provider.addClient(restaurant);
                });
    }
}
