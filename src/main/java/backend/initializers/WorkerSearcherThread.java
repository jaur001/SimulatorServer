package backend.initializers;

import backend.implementations.worker.GenericWorkerSearcher;
import backend.implementations.worker.strategy.strategies.FirstWorkerStrategy;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.Simulation;

public class WorkerSearcherThread{

    public static void addWorkers(){
        Simulation.getRestaurantListCopy().parallelStream().forEach(WorkerSearcherThread::addWorker);
    }

    public static void addWorker(Restaurant restaurant){
        new GenericWorkerSearcher(new FirstWorkerStrategy()).createStaff(restaurant);
    }

}
