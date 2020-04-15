package backend.implementations.workerSearcher.strategy.strategies.complexStrategy;

import backend.implementations.workerSearcher.strategy.strategies.GenericWorkerStrategy;
import backend.model.simulables.person.worker.Worker;

import java.util.List;

public abstract class ComplexWorkerStrategy extends GenericWorkerStrategy {
    @Override
    protected Worker getWorker(List<Worker> unemployedWorkers) {
        if(unemployedWorkers.size() == 0) return null;
        if(unemployedWorkers.size() == 1) return unemployedWorkers.get(0);
        return unemployedWorkers.stream().reduce(unemployedWorkers.get(0), this::getBetterWorker);
    }
}
