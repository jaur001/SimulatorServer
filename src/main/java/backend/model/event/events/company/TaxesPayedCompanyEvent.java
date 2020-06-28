package backend.model.event.events.company;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.Company;
import backend.utils.EuroFormatter;

public class TaxesPayedCompanyEvent extends GenericEvent<Company> {

    public TaxesPayedCompanyEvent(Company company) {
        super(company);
    }

    @Override
    public String getMessage() {
        return "The company " + simulable.getName() + " has payed the taxes. Amount: " + getAmount();
    }

    private String getAmount() {
        return EuroFormatter.formatEuro(simulable.getFinancialData().getLastMonthBenefits()*Company.getTaxes());
    }
}
