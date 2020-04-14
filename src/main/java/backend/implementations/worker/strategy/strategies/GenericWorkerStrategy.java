package backend.implementations.worker.strategy.strategies;

import backend.implementations.worker.strategy.WorkerStrategy;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.Simulation;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericWorkerStrategy implements WorkerStrategy {
    @Override
    public Worker getWorker(Job job) {
        List<Worker> unemployedWorkers = Simulation.getUnemployedWorkers(job);
        return getWorker(unemployedWorkers);
    }

    @Override
    public Worker getWorker(Job job, List<Worker> workerList) {
        List<Worker> unemployedWorkers = Simulation.getUnemployedWorkers(job);
        List<Worker> auxList = new ArrayList<>(unemployedWorkers);
        auxList.stream().filter(workerList::contains).forEach(unemployedWorkers::remove);
        return getWorker(unemployedWorkers);
    }

    protected abstract Worker getWorker(List<Worker> unemployedWorkers);

    @Override
    public abstract Worker getBetterWorker(Worker worker1, Worker worker2);

}
