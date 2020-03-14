package backend.view.loaders.worker;

import backend.model.simulables.restaurant.worker.Worker;

import java.util.List;

public interface WorkerLoader {
    List<Worker> load(int numTables);
}
