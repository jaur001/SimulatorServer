package backend.threadsInitializers.provider;

import backend.implementations.loaders.providing.RandomProvidingController;
import backend.model.simulables.provider.Product;
import backend.model.simulables.provider.Provider;
import backend.model.simulables.restaurant.Restaurant;

import java.util.List;
import java.util.stream.IntStream;

public class ProvidingThread{


    public static void initRestaurantsProviders(List<Provider> providerList, List<Restaurant> restaurantList){
        restaurantList.parallelStream().forEach(restaurant -> initProvidersForRestaurant(restaurant,providerList));
    }

    private static void initProvidersForRestaurant(Restaurant restaurant, List<Provider> providerList) {
        IntStream.range(0, Product.values().length).boxed()
                .map(integer -> new RandomProvidingController().provide(providerList,Product.values()[integer]))
                .forEach(provider -> {
                    restaurant.addProvider(provider);
                    provider.addRestaurant(restaurant);
                });
    }
}
