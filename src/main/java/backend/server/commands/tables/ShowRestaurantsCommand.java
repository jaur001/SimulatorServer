package backend.server.commands.tables;


import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowRestaurantsCommand extends PageableFrontCommand<Restaurant> {

    @Override
    public void process() {
        checkPagination();
        forward("/restaurants.jsp");
    }

    @Override
    protected String getName() {
        return "restaurantList";
    }

    @Override
    protected List<Restaurant> loadList() {
        return Simulation.getRestaurantListCopy();
    }

}
