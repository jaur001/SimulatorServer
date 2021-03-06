package backend.implementations.workerSearcher;

import backend.implementations.workerSearcher.strategy.WorkerStrategy;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.administration.WorkerSearcher;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class GenericWorkerSearcher implements WorkerSearcher {

    private WorkerStrategy strategy;

    public GenericWorkerSearcher(WorkerStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void createStaff(Restaurant restaurant) {
        Arrays.stream(Job.values())
                .forEach(job -> searchWorkers(job,restaurant));
    }

    private void searchWorkers(Job job, Restaurant restaurant) {
        int numWorkers = RestaurantSettings.getWorkerLength(job,restaurant.getTables());
        IntStream.range(0,numWorkers).boxed()
                .map(integer -> strategy.getWorker(job))
                .forEach(worker -> {
                    if(worker!=null)restaurant.getAdministrator().addWorker(worker);
                });
    }

    @Override
    public List<Worker> searchBetterOptions(Worker worker) {
        List<Worker> options = new LinkedList<>();
        int count = 0;
        while (count < 10){
            Worker option = strategy.getWorker(Job.valueOf(worker.getJob()),options);
            if (option == null) break;
            if(isBetterOption(worker,option)) options.add(option);
            else break;
            count++;
        }
        return options;
    }

    @Override
    public Worker searchBestOptions(Job job) {
        return strategy.getWorker(job);
    }

    private boolean isBetterOption(Worker worker, Worker option) {
        double salaryPerQuality = RestaurantSettings.getSalaryPerQuality(option);
        double salaryPerQuality2 = RestaurantSettings.getSalaryPerQuality(worker);
        return salaryPerQuality > salaryPerQuality2;
    }
}
