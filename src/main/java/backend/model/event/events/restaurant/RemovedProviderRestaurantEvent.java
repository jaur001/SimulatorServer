package backend.model.event.events.restaurant;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;

public class RemovedProviderRestaurantEvent extends GenericEvent<Restaurant> {

    private Provider provider;

    public RemovedProviderRestaurantEvent(Restaurant restaurant, Provider provider) {
        super(restaurant);
        this.provider = provider;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " removed Provider: " + provider.getCompanyName()
                + " with a cost of " + provider.getPrice() + ".";
    }
}
