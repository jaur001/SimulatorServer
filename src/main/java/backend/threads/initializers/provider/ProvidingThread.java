package backend.threads.initializers.provider;

import backend.implementations.loaders.provider.RandomProvidingController;
import backend.model.provider.Product;
import backend.model.provider.Provider;
import backend.model.restaurant.Restaurant;

import java.util.List;
import java.util.stream.IntStream;

public class ProvidingThread{


    public static void initRestaurantProviders(List<Provider> providerList, List<Restaurant> restaurantList){
        restaurantList.parallelStream().forEach(restaurant1 -> initProviders(restaurant1,providerList));
    }

    private static void initProviders(Restaurant restaurant, List<Provider> providerList) {
        IntStream.range(0, Product.values().length).boxed()
                .map(integer -> new RandomProvidingController().provide(providerList,Product.values()[integer]))
                .forEach(restaurant::addProvider);
    }
}
