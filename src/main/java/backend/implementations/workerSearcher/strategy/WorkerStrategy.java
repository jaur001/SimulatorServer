package backend.implementations.workerSearcher.strategy;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;

import java.util.List;

public interface WorkerStrategy {
    Worker getWorker(Job job);
    Worker getWorker(Job job, List<Worker> workerList);
    Worker getBetterWorker(Worker worker1, Worker worker2);
}
