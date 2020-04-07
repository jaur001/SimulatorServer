package backend.implementations.worker.strategy;


import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FirstWorkerStrategy implements WorkerStrategy {
    @Override
    public List<Worker> getWorker(Job job, int amount) {
        return IntStream.range(0,amount).boxed()
                .map(integer -> getWorker(job))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Worker getWorker(Job job) {
        List<Worker> unemployedWorkers = Simulation.getUnemployedWorkers(job);
        Worker worker = unemployedWorkers.stream()
                .findFirst().orElse(null);
        if(worker != null) worker.lock();
        return worker;
    }
}
