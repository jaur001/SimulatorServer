package backend.implementations.worker.strategy;

import backend.model.simulables.person.worker.Worker;

import java.util.List;

public interface WorkerStrategy {
    Worker getWorker(List<Worker> unemployedWorkers);
}
