package backend.server.frontcontroller;


import backend.model.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsCommand extends FrontCommand {
    @Override
    public void process() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant());
        restaurantList.add(new Restaurant());
        restaurantList.add(new Restaurant());
        request.setAttribute("restaurantList",restaurantList);
        forward("/restaurants.jsp");
    }
}
