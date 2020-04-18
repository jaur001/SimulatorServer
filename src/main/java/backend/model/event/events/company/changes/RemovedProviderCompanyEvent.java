package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;

public class RemovedProviderCompanyEvent extends GenericEvent<ComplexCompany> {

    private Provider provider;

    public RemovedProviderCompanyEvent(ComplexCompany company, Provider provider) {
        super(company);
        this.provider = provider;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " removed Provider: " + provider.getCompanyName()
                + " with a cost of " + provider.getPrice() + ".";
    }
}
