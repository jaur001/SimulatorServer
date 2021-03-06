package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.utils.EuroFormatter;

public class RemovedProviderCompanyEvent extends GenericEvent<ComplexCompany> {

    private Provider provider;

    public RemovedProviderCompanyEvent(ComplexCompany company, Provider provider) {
        super(company);
        this.provider = provider;
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " removed Provider: " + provider.getName()
                + " with a cost of " + EuroFormatter.formatEuro(provider.getPrice()) + ".";
    }
}
