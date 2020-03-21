package backend.threadsInitializers;

import backend.implementations.loaders.worker.EnumWorkerLoader;
import backend.model.simulables.restaurant.Restaurant;

import java.util.List;

public class WorkerThread extends Thread{

    public static void addWorkers(List<Restaurant> restaurantList){
        restaurantList.parallelStream().forEach(WorkerThread::addWorker);
    }

    private static void addWorker(Restaurant restaurant){
        new EnumWorkerLoader().load(restaurant.getTables())
                .forEach(restaurant::addWorker);
    }

}
