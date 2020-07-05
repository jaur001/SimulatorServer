package backend.server.commands.search;

import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.servlets.SearchableFrontCommand;
import backend.utils.SearchUtils;

import java.util.List;

public class SearchRestaurantsCommand extends SearchableFrontCommand<Restaurant> {

    @Override
    public List<Restaurant> search(String text, SearchBy searchBy) {
        return Search.searchRestaurants(SearchUtils.getCompanyFilter(searchBy).search(text));
    }

    @Override
    public void process() {
        checkPagination();
        forward("/restaurants.jsp");
    }

    @Override
    protected String getName() {
        return "restaurantList";
    }
}