package backend.model.event.events.company.changes;

import backend.model.event.GenericEvent;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.person.worker.Worker;

public class WorkerFiredCompanyEvent extends GenericEvent<ComplexCompany> {

    private Worker worker;

    public WorkerFiredCompanyEvent(ComplexCompany company, Worker worker) {
        super(company);
        this.worker = worker;
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " has fired the " + worker.getJob() +": " + worker.getName()
                + " with Quality " + worker.getQuality() + " and Salary: " + worker.getSalary() + ".";
    }
}
