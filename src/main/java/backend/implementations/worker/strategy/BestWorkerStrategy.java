package backend.implementations.worker.strategy;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.RestaurantSettings;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BestWorkerStrategy implements WorkerStrategy {

    @Override
    public List<Worker> getWorker(Job job, int amount) {
        return IntStream.range(0,amount).boxed()
                .map(integer -> getWorker(job))
                .collect(Collectors.toCollection(LinkedList::new));
    }
    @Override
    public Worker getWorker(Job job) {
        List<Worker> unemployedWorkers = Simulation.getUnemployedWorkers(job);
        if(unemployedWorkers.size() == 0) return null;
        if(unemployedWorkers.size() == 1) return unemployedWorkers.get(0);
        Worker worker = unemployedWorkers.stream().reduce(unemployedWorkers.get(0), this::getBetterWorker);
        worker.lock();
        return worker;
    }

    private Worker getBetterWorker(Worker worker1, Worker worker2) {
        return RestaurantSettings.getSalaryPerQuality(worker1) >= RestaurantSettings.getSalaryPerQuality(worker2)? worker1: worker2;
    }



}
