package backend.model.event.events.company.monthlyCompany;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.ServiceCompany;

public class ClientRemovedServiceEvent extends GenericEvent<ServiceCompany> {

    private ComplexCompany company;

    public ClientRemovedServiceEvent(ServiceCompany serviceCompany, ComplexCompany company) {
        super(serviceCompany);
        this.company = company;
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " has lost the client, " + company.getName() + ".";
    }
}
