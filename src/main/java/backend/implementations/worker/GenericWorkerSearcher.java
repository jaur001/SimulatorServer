package backend.implementations.worker;

import backend.implementations.worker.strategy.WorkerStrategy;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.view.loaders.WorkerSearcher;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenericWorkerSearcher implements WorkerSearcher {

    private WorkerStrategy strategy;

    public GenericWorkerSearcher(WorkerStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<Worker> createStaff(int numTables) {
        return Arrays.stream(Job.values())
                .flatMap(job -> searchWorkers(job,numTables).stream())
                .collect(Collectors.toList());
    }

    private List<Worker> searchWorkers(Job job, int numTables) {
        int numWorkers = RestaurantSettings.getWorkerLength(job,numTables);
        List<Worker> unemployedWorkers = Simulation.getUnemployedWorkers(job);
        return IntStream.range(0, numWorkers).boxed()
                .map(integer -> strategy.getWorker(unemployedWorkers))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Worker search(Job job) {
        List<Worker> unemployedWorkers = Simulation.getUnemployedWorkers(job);
        if(unemployedWorkers.size() == 1) return unemployedWorkers.get(0);
        return strategy.getWorker(unemployedWorkers);
    }

    @Override
    public List<Worker> searchBetterOptions(Worker worker) {
        List<Worker> unemployedWorkers = Simulation.getUnemployedWorkers(Job.valueOf(worker.getJob()));
        return unemployedWorkers.stream().filter(option -> isBetterOption(worker,option)).collect(Collectors.toCollection(LinkedList::new));
    }

    private boolean isBetterOption(Worker worker, Worker option) {
        return RestaurantSettings.getSalaryPerQuality(option)<RestaurantSettings.getSalaryPerQuality(worker);
    }
}
