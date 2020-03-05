package backend.main;

import backend.implementations.xmlBills.CFDIBillGenerator;
import backend.model.restaurant.Restaurant;
import backend.threads.initializers.RestaurantThread;
import backend.threads.initializers.WorkerThread;

import java.util.List;

public class WorkersMain {

    public static void main(String[] args) {
        int count = 1;
        int rowNumber = 2;
        String urlClient = "./clientsFile/ExportCSV.csv";
        CFDIBillGenerator.setUrl("./xmlFiles/");

        List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        WorkerThread.addWorkers(restaurantList);
        restaurantList.stream()
                .forEach(restaurant -> restaurant.getWorkerList().stream()
                    .forEach(worker -> System.out.println("Worker: " + worker.getJob()))
                );
    }
}
