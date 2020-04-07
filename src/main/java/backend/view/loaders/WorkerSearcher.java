package backend.view.loaders;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;

import java.util.List;

public interface WorkerSearcher {
    List<Worker> createStaff(int numTables);
    List<Worker> searchBetterOptions(Worker worker);
}
