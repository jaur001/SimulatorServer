package backend.implementations.worker.strategy;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;

import java.util.List;

public interface WorkerStrategy {
    Worker getWorker(Job job);
    Worker getWorker(Job job, List<Worker> workerList);
}
