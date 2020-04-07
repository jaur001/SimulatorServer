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
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private List<Worker> searchWorkers(Job job, int numTables) {
        int numWorkers = RestaurantSettings.getWorkerLength(job,numTables);
        return strategy.getWorker(job,numWorkers);
    }

    @Override
    public List<Worker> searchBetterOptions(Worker worker) {
        List<Worker> options = new LinkedList<>();
        while (true){
            Worker option = strategy.getWorker(Job.valueOf(worker.getJob()));
            if (option == null) break;
            if(isBetterOption(worker,option)) options.add(option);
            else break;
        }
        Simulation.unlockUnemployedWorkers();
        return options;
    }

    private boolean isBetterOption(Worker worker, Worker option) {
        double salaryPerQuality = RestaurantSettings.getSalaryPerQuality(option);
        double salaryPerQuality2 = RestaurantSettings.getSalaryPerQuality(worker);
        return salaryPerQuality > salaryPerQuality2;
    }
}
