package backend.implementations.loaders.worker;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.view.loaders.WorkerSearcher;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomWorkerSearcher implements WorkerSearcher {


    @Override
    public List<Worker> search(int numTables) {
        return Arrays.stream(Job.values())
                .flatMap(job -> searchWorkersForGroup(job,numTables).stream())
                .collect(Collectors.toList());
    }

    private List<Worker> searchWorkersForGroup(Job job, int numTables) {
        int numWorkers = RestaurantSettings.getWorkerLength(job,numTables);
        return IntStream.range(0, numWorkers).boxed()
                .map(integer -> getWorker(job))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Worker getWorker(Job job) {
        return Simulation.getWorkerList(job).stream().filter(worker -> !worker.isWorking()).findFirst().orElse(null);
    }
}
