package backend.server.commands;


import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.model.simulables.restaurant.Restaurant;
import backend.model.simulation.Simulation;
import backend.server.frontcontroller.FrontCommand;
import backend.threads.initializers.RestaurantThread;

import java.util.List;

public class ShowRestaurantsCommand extends FrontCommand {
    @Override
    public void process() {
        request.setAttribute("restaurantList", Simulation.getRestaurantList());
        forward("/restaurants.jsp");
    }
}
