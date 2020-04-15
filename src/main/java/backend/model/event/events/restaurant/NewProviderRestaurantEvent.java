package backend.model.event.events.restaurant;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;

public class NewProviderRestaurantEvent extends GenericEvent<Restaurant> {

    private Provider provider;

    public NewProviderRestaurantEvent(Restaurant restaurant, Provider provider) {
        super(restaurant);
        this.provider = provider;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " has new Provider: " + provider.getCompanyName()
                + " with a cost of " + provider.getProductPrice() + ".";
    }
}
