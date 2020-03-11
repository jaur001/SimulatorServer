package backend.server.commands;


import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.model.restaurant.Restaurant;
import backend.server.frontcontroller.FrontCommand;

import java.util.List;

public class RestaurantsCommand extends FrontCommand {
    @Override
    public void process() {
        SQLiteRestaurantReader.setSQLiteUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));
        List<Restaurant> restaurantList = new SQLiteRestaurantReader().read(100);
        request.setAttribute("restaurantList",restaurantList);
        forward("/restaurants.jsp");
    }
}
