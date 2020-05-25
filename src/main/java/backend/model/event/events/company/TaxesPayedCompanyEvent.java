package backend.model.event.events.company;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.Company;

public class TaxesPayedCompanyEvent extends GenericEvent<Company> {

    public TaxesPayedCompanyEvent(Company company) {
        super(company);
    }

    @Override
    public String getMessage() {
        return "The company " + simulable.getName() + " has payed the taxes. Amount: " + simulable.getFinancialData().getLastMonthBenefits()*Company.getTaxes();
    }
}
