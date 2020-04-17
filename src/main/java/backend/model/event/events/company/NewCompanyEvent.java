package backend.model.event.events.company;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;

public class NewCompanyEvent extends GenericEvent<ComplexCompany> {

    public NewCompanyEvent(ComplexCompany company) {
        super(company);
    }

    @Override
    public String getMessage() {
        return "The company " + simulable.getCompanyName() + " has opened. NIF: " + simulable.getNIF();
    }
}
