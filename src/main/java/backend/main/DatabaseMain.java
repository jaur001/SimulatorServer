package backend.main;

import backend.implementations.loaders.restaurant.SQLite.SQLRestaurantWriter;
import backend.model.restaurant.Restaurant;
import backend.model.threads.initializers.RestaurantThread;

import java.util.List;

public class DatabaseMain {

    public static void main(String[] args) {
        int count = 50;
        String url = "jdbc:sqlite:Simulator.db";
        SQLRestaurantWriter.setSQLiteUrl(url);
        List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        new SQLRestaurantWriter().write(restaurantList);
    }
}
