package backend.implementations.loaders.worker;

import backend.model.simulables.restaurant.worker.Job;
import backend.model.simulables.restaurant.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.view.loaders.worker.WorkerLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EnumWorkerLoader implements WorkerLoader {


    @Override
    public List<Worker> load(int numTables) {
        return Arrays.stream(Job.values())
                .flatMap(job -> loadWorkersGroup(job,numTables).stream())
                .collect(Collectors.toList());
    }

    private List<Worker> loadWorkersGroup(Job job,int numTables) {
        int numWorkers = RestaurantSettings.getWorkerLengthGroup(job,numTables);
        return IntStream.range(0,numWorkers).boxed()
                .map(integer -> new Worker(RestaurantSettings.getWorkerSalaryGroup(job),job))
                .collect(Collectors.toList());
    }
}
