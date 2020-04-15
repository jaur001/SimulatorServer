package backend.model.event.provider;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;

public class NewClientProviderEvent extends GenericEvent<Provider> {

    private Restaurant restaurant;

    public NewClientProviderEvent(Provider provider, Restaurant restaurant) {
        super(provider);
        this.restaurant = restaurant;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " has new Client: " + restaurant.getCompanyName() + ", Price: " + simulable.getProductPrice();
    }
}
