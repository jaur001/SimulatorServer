package backend.threads.bills;

import backend.implementations.xmlBills.CFDIPayrollGenerator;
import backend.model.restaurant.Restaurant;
import backend.model.restaurant.worker.Payroll;
import backend.model.restaurant.worker.Worker;

import java.util.List;

public class WorkerPayrollThread extends Thread{

    public static void payWorkers(List<Restaurant> restaurantList){
        restaurantList.parallelStream().forEach(WorkerPayrollThread::payWorkers);
    }

    private static void payWorkers(Restaurant restaurant){
        restaurant.getWorkerList()
                .forEach(worker -> getPayroll(worker,restaurant));
    }

    private static void getPayroll(Worker worker, Restaurant restaurant) {
        restaurant.payWorker(worker);
        new CFDIPayrollGenerator().generatePayroll(new Payroll(worker,restaurant));
    }
}
