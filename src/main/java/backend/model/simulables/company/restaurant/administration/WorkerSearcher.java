package backend.model.simulables.company.restaurant.administration;

import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;

import java.util.List;

public interface WorkerSearcher {
    void createStaff(Restaurant restaurant);
    List<Worker> searchBetterOptions(Worker worker);
    Worker searchBestOptions(Job job);
}
