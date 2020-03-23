package backend.threadsInitializers;

import backend.model.simulables.restaurant.Restaurant;
import backend.implementations.loaders.tripAvisor.TripAdvisorRestaurantReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RestaurantThread{

    public static List<Restaurant> loadRestaurantsPage(int threadsCount){
        return IntStream.range(0,threadsCount).boxed()
                .parallel().map(RestaurantThread::getPage)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    private static List<Restaurant> getPage(int page){
        try {
            return new TripAdvisorRestaurantReader().read(page);
        } catch (IOException e) {
            System.out.println("Error: Time Out exception");
        }
        return new LinkedList<>();
    }

}
