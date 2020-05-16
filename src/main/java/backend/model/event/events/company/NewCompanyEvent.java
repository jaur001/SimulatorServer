package backend.model.event.events.company;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.complexCompany.ComplexCompany;

public class NewCompanyEvent extends GenericEvent<ComplexCompany> {

    public NewCompanyEvent(ComplexCompany company) {
        super(company);
    }

    @Override
    public String getMessage() {
        return "The company " + simulable.getName() + " has opened. NIF: " + simulable.getNIF();
    }
}
