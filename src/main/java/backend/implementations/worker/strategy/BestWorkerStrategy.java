package backend.implementations.worker.strategy;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.RestaurantSettings;

import java.util.List;

public class BestWorkerStrategy implements WorkerStrategy {
    @Override
    public Worker getWorker(List<Worker> unemployedWorkers) {

        return unemployedWorkers.stream().reduce(unemployedWorkers.get(0), this::getBetterWorker);
    }

    private Worker getBetterWorker(Worker worker1, Worker worker2) {
        return RestaurantSettings.getSalaryPerQuality(worker1)<=RestaurantSettings.getSalaryPerQuality(worker2)? worker1: worker2;
    }


}
