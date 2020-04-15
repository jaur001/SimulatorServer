package backend.model.event.events.worker;

import backend.model.event.GenericEvent;
import backend.model.simulables.person.worker.Worker;

public class HiredWorkerEvent extends GenericEvent<Worker> {

    public HiredWorkerEvent(Worker worker) {
        super(worker);
    }

    @Override
    public String getMessage() {
        return simulable.getFullName() + " has been hired for a salary of "+ simulable.getSalary() + "€.";

    }
}
