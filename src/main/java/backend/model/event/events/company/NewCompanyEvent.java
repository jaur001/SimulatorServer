package backend.model.event.events.company;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.Company;

public class NewCompanyEvent extends GenericEvent<Company> {

    public NewCompanyEvent(Company company) {
        super(company);
    }

    @Override
    public String getMessage() {
        return "The company " + simulable.getCompanyName() + " has opened. NIF: " + simulable.getNIF();
    }
}
