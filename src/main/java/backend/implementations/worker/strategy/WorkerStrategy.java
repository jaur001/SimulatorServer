package backend.implementations.worker.strategy;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;

import java.util.List;

public interface WorkerStrategy {
    List<Worker> getWorker(Job job, int amount);
    Worker getWorker(Job job);
}
