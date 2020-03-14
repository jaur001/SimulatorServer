package backend.server.commands;


import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.model.simulables.restaurant.Restaurant;
import backend.server.frontcontroller.FrontCommand;
import backend.threads.initializers.RestaurantThread;

import java.util.List;

public class RestaurantsCommand extends FrontCommand {
    @Override
    public void process() {
        SQLiteRestaurantReader.setSQLiteUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));
        //List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(1);
        List<Restaurant> restaurantList = new SQLiteRestaurantReader().read(30);
        request.setAttribute("restaurantList",restaurantList);
        forward("/restaurants.jsp");
    }
}
