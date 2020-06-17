package backend.initializers;

import backend.implementations.workerSearcher.GenericWorkerSearcher;
import backend.implementations.workerSearcher.strategy.strategies.FirstWorkerStrategy;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulation.administration.centralControl.Simulation;

public class WorkerSearcherThread{

    public static void addWorkers(){
        Simulation.getRestaurantListCopy().parallelStream().forEach(WorkerSearcherThread::addWorker);
    }

    public static void addWorker(Restaurant restaurant){
        new GenericWorkerSearcher(new FirstWorkerStrategy()).createStaff(restaurant);
    }

}
