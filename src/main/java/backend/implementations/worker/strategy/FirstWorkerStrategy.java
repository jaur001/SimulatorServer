package backend.implementations.worker.strategy;


import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;

import java.util.ArrayList;
import java.util.List;

public class FirstWorkerStrategy implements WorkerStrategy {

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

    private Worker getWorker(List<Worker> unemployedWorkers) {
        return unemployedWorkers.stream()
                .findFirst().orElse(null);
    }
}
