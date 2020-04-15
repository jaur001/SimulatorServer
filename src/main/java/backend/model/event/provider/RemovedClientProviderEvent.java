package backend.model.event.provider;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;

public class RemovedClientProviderEvent extends GenericEvent<Provider> {

private Restaurant restaurant;

    public RemovedClientProviderEvent(Provider provider, Restaurant restaurant) {
        super(provider);
        this.restaurant = restaurant;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " lost Client: " + restaurant.getCompanyName() + ", Price: " + simulable.getPrice();
    }
}
