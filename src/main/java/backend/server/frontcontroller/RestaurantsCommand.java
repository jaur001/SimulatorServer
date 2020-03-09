package backend.server.frontcontroller;


import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.model.restaurant.Restaurant;
import backend.threads.initializers.RestaurantThread;

import java.util.List;

public class RestaurantsCommand extends FrontCommand {
    @Override
    public void process() {
        SQLiteRestaurantReader.setSQLiteUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));
        List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(1);
        request.setAttribute("restaurantList",restaurantList);
        forward("/restaurants.jsp");
    }
}
