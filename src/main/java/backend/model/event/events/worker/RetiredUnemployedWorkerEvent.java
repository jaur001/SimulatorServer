package backend.model.event.events.worker;

import backend.model.event.GenericEvent;
import backend.model.simulables.person.worker.Worker;

public class RetiredUnemployedWorkerEvent extends GenericEvent<Worker> {


    public RetiredUnemployedWorkerEvent(Worker worker) {
        super(worker);
    }

    @Override
    public String getMessage() {
        return simulable.getName() + " has retired.";
    }
}
