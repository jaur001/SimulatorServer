package backend.model.event.events.company.monthlyCompany;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.provider.Provider;

public class ClientRemovedProviderEvent extends GenericEvent<Provider> {

    private ComplexCompany company;

    public ClientRemovedProviderEvent(Provider provider, ComplexCompany company) {
        super(provider);
        this.company = company;
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " has lost the client, " + company.getName() + ".";
    }
}
