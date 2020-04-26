package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;

public class RemovedServiceCompanyEvent extends GenericEvent<ComplexCompany> {

    private ServiceCompany serviceCompany;

    public RemovedServiceCompanyEvent(ComplexCompany company, ServiceCompany serviceCompany) {
        super(company);
        this.serviceCompany = serviceCompany;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " removed Service: " + serviceCompany.getCompanyName()
                + " with a cost of " + serviceCompany.getPrice() + ".";
    }
}