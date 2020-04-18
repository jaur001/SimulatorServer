package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;

public class WorkerHiredCompanyEvent extends GenericEvent<ComplexCompany> {

    private Worker worker;

    public WorkerHiredCompanyEvent(ComplexCompany company, Worker worker) {
        super(company);
        this.worker = worker;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " has hired the " + worker.getJob() +": " + worker.getFullName()
                + " with Quality " + worker.getQuality() + "and Salary: " + worker.getSalary() + ".";
    }
}
