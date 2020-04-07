package backend.implementations.worker.strategy;


import backend.model.simulables.person.worker.Worker;

import java.util.List;

public class FirstWorkerStrategy implements WorkerStrategy {
    @Override
    public Worker getWorker(List<Worker> unemployedWorkers) {
        return unemployedWorkers.stream()
                .findFirst().orElse(null);
    }
}
