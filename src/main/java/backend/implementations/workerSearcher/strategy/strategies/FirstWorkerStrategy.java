package backend.implementations.workerSearcher.strategy.strategies;


import backend.model.simulables.person.worker.Worker;

import java.util.List;

public class FirstWorkerStrategy extends GenericWorkerStrategy {

    @Override
    protected Worker getWorker(List<Worker> unemployedWorkers) {
        return unemployedWorkers.stream()
                .findFirst().orElse(null);
    }

    @Override
    public Worker getBetterWorker(Worker worker1, Worker worker2) {
        return worker1;
    }
}
