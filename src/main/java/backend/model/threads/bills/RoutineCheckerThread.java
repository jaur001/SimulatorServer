package backend.model.threads.bills;

import backend.implementations.loaders.routine.DistributionAmountGenerator;
import backend.implementations.xmlBills.CFDIBillGenerator;
import backend.model.client.Client;
import backend.model.restaurant.PlateBill;
import backend.model.restaurant.Eating;
import backend.model.restaurant.Restaurant;

import java.util.List;

public class RoutineCheckerThread extends Thread{

    public static void checkRoutines(List<Client> clientList){
        clientList.parallelStream().forEach(RoutineCheckerThread::checkRoutines);
    }

    private static void checkRoutines(Client client){
        client.getRoutineList().checkRoutines()
                .forEach(restaurant -> getBill(restaurant,client));
    }

    private static void getBill(Restaurant restaurant, Client client) {
        double amount = new DistributionAmountGenerator().generate(restaurant,client);
        client.pay(amount);
        restaurant.addSale(amount);
        new CFDIBillGenerator().generateBill(new Eating(restaurant,client,new PlateBill(amount),client.getCommensalNumber()));
    }
}