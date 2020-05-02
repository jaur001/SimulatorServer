package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;

public class NewServiceCompanyEvent extends GenericEvent<ComplexCompany> {

    private ServiceCompany serviceCompany;

    public NewServiceCompanyEvent(ComplexCompany company, ServiceCompany serviceCompany) {
        super(company);
        this.serviceCompany = serviceCompany;
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " has new Service: " + serviceCompany.getName()
                + " with a cost of " + serviceCompany.getPrice() + ".";
    }
}
