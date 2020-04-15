package backend.model.event.events.worker;

import backend.model.event.GenericEvent;
import backend.model.simulables.person.worker.Worker;

public class RetiredWorkerEvent extends GenericEvent<Worker> {

    public RetiredWorkerEvent(Worker worker) {
        super(worker);
    }

    @Override
    public String getMessage() {
        return simulable.getFullName() + " has retired.";
    }
}
