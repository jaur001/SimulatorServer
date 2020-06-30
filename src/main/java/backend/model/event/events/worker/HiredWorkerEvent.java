package backend.model.event.events.worker;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.person.worker.Worker;
import backend.utils.EuroFormatter;

public class HiredWorkerEvent extends GenericEvent<Worker> {

    private ComplexCompany company;

    public HiredWorkerEvent(Worker worker, ComplexCompany company) {
        super(worker);
        this.company = company;
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " has been hired in " + company.getName() + " with a salary of "+ EuroFormatter.formatEuro(simulable.getSalary()) + ".";

    }
}
