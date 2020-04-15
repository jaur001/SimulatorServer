package backend.model.event.events.worker;

import backend.model.event.GenericEvent;
import backend.model.simulables.person.worker.Worker;

public class FiredWorkerEvent extends GenericEvent<Worker> {

    public FiredWorkerEvent(Worker worker) {
        super(worker);
    }

    @Override
    public String getMessage() {
        return simulable.getFullName() + " has been fired.";
    }
}
