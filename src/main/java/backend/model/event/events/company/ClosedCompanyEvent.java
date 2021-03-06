package backend.model.event.events.company;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.Company;

public class ClosedCompanyEvent extends GenericEvent<Company> {

    public ClosedCompanyEvent(Company company) {
        super(company);
    }

    @Override
    public String getMessage() {
        return "The company " + simulable.getName() + " has closed. NIF: " + simulable.getNIF();
    }
}
