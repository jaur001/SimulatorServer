package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.person.worker.Worker;

public class WorkerRetiredCompanyEvent extends GenericEvent<ComplexCompany> {

    private Worker worker;

    public WorkerRetiredCompanyEvent(ComplexCompany company, Worker worker) {
        super(company);
        this.worker = worker;
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " has retired the " + worker.getJob() +": " + worker.getName()
                + " with a pension of " + worker.getSalary() + ".";
    }
}