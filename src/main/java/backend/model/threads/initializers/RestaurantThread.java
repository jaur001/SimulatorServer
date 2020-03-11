package backend.model.threads.initializers;

import backend.model.restaurant.Restaurant;
import backend.implementations.loaders.restaurant.tripAvisor.TripAdvisorRestaurantReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RestaurantThread{

    public static List<Restaurant> loadRestaurantsPage(int threadsCount){
        return IntStream.range(0,threadsCount).boxed()
                .parallel().map(RestaurantThread::getPage)
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }

    private static List<Restaurant> getPage(int page){
        try {
            return new TripAdvisorRestaurantReader().read(page);
        } catch (IOException e) {
            System.out.println("Error: Time Out exception");
        }
        return new ArrayList<>();
    }

}
