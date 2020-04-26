package backend.model.event.events.company.monthlyCompany;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;

public class ClientRemovedServiceEvent extends GenericEvent<ServiceCompany> {

    private ComplexCompany company;

    public ClientRemovedServiceEvent(ServiceCompany serviceCompany, ComplexCompany company) {
        super(serviceCompany);
        this.company = company;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " has lost the client, " + company.getCompanyName() + ".";
    }
}