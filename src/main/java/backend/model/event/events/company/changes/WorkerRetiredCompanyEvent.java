package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;

public class WorkerRetiredCompanyEvent extends GenericEvent<ComplexCompany> {

    private Worker worker;

    public WorkerRetiredCompanyEvent(ComplexCompany company, Worker worker) {
        super(company);
        this.worker = worker;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " has retired the " + worker.getJob() +": " + worker.getFullName()
                + " with a pension of " + worker.getSalary() + ".";
    }
}