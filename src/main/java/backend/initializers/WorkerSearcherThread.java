package backend.initializers;

import backend.implementations.loaders.worker.RandomWorkerSearcher;
import backend.model.simulables.company.restaurant.Restaurant;

import java.util.List;

public class WorkerSearcherThread extends Thread{

    public static void addWorkers(List<Restaurant> restaurantList){
        restaurantList.parallelStream().forEach(WorkerSearcherThread::addWorker);
    }

    private static void addWorker(Restaurant restaurant){
        new RandomWorkerSearcher().search(restaurant.getTables()).forEach(restaurant::addWorker);
    }

}
