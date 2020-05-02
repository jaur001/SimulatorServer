package backend.model.event.events.worker;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.worker.Worker;

public class RetiredWorkerEvent extends GenericEvent<Worker> {

    private ComplexCompany company;

    public RetiredWorkerEvent(Worker worker, ComplexCompany company) {
        super(worker);
        this.company = company;
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " has retired from " + company.getName() + ".";
    }
}
