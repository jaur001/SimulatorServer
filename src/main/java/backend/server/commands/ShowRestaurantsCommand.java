package backend.server.commands;


import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;

public class ShowRestaurantsCommand extends FrontCommand {
    @Override
    public void process() {
        request.setAttribute("restaurantList", Simulation.getRestaurantList());
        forward("/restaurants.jsp");
    }
}
