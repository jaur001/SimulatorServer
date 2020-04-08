package backend.implementations.worker.strategy;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.RestaurantSettings;

import java.util.ArrayList;
import java.util.List;

public class BestWorkerStrategy implements WorkerStrategy {

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
        if(unemployedWorkers.size() == 0) return null;
        if(unemployedWorkers.size() == 1) return unemployedWorkers.get(0);
        return unemployedWorkers.stream().reduce(unemployedWorkers.get(0), this::getBetterWorker);
    }

    private Worker getBetterWorker(Worker worker1, Worker worker2) {
        return RestaurantSettings.getSalaryPerQuality(worker1) >= RestaurantSettings.getSalaryPerQuality(worker2)? worker1: worker2;
    }



}
