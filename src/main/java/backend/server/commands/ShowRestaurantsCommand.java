package backend.server.commands;


import backend.model.simulables.restaurant.Restaurant;
import backend.model.simulation.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowRestaurantsCommand extends PageableFrontCommand<Restaurant> {

    public static final String TABLE_NAME = "Restaurant";

    @Override
    public void process() {
        checkPagination();
        forward("/restaurants.jsp");
    }

    @Override
    protected List<Restaurant> getList(int page) {
        return Simulation.getRestaurantList(page);
    }

    @Override
    protected int getLimit(){
        return Simulation.getRestaurantSize();
    }

}
