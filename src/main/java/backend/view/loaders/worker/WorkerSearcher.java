package backend.view.loaders.worker;

import backend.model.simulables.person.worker.Worker;

import java.util.List;

public interface WorkerSearcher {
    List<Worker> search(int numTables);
}
