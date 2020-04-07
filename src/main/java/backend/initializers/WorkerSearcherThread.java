package backend.initializers;

import backend.implementations.worker.GenericWorkerSearcher;
import backend.implementations.worker.strategy.FirstWorkerStrategy;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.Simulation;

public class WorkerSearcherThread extends Thread{

    public static void addWorkers(){
        Simulation.getRestaurantList().parallelStream().forEach(WorkerSearcherThread::addWorker);
    }

    private static void addWorker(Restaurant restaurant){
        new GenericWorkerSearcher(new FirstWorkerStrategy()).createStaff(restaurant.getTables()).forEach(worker -> restaurant.addWorker(worker,worker.getSalaryDesired()));
    }

}
