package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;

public class WorkerFiredCompanyEvent extends GenericEvent<ComplexCompany> {

    private Worker worker;

    public WorkerFiredCompanyEvent(ComplexCompany company, Worker worker) {
        super(company);
        this.worker = worker;
    }

    @Override
    public String getMessage() {
        return simulable.getCompanyName() + " has fired the " + worker.getJob() +": " + worker.getFullName()
                + " with Quality " + worker.getQuality() + "and Salary: " + worker.getSalary() + ".";
    }
}
