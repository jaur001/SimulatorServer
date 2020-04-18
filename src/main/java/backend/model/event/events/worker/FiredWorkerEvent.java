package backend.model.event.events.worker;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.worker.Worker;

public class FiredWorkerEvent extends GenericEvent<Worker> {

    private ComplexCompany company;

    public FiredWorkerEvent(Worker worker, ComplexCompany company) {
        super(worker);
        this.company = company;
    }

    @Override
    public String getMessage() {
        return simulable.getFullName() + " has been fired from " + company.getCompanyName() + ".";
    }
}
