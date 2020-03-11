package backend.model.time;

import backend.model.client.Client;
import backend.model.provider.Provider;
import backend.model.restaurant.Restaurant;
import backend.model.threads.BudgetRestartThread;
import backend.model.threads.bills.WorkerPayrollThread;

import java.util.List;

public class Year {
    private static Month[] yearMonths = new Month[12];
    private static int actualMonth = 1;
    private int lastMonth = 12;



    public boolean passTime(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList) {
        if(yearMonths[actualMonth-1].passTime(restaurantList,clientList,providerList)){
            actualMonth = ++actualMonth>lastMonth?1 : actualMonth;
            System.out.println("New Month: " + actualMonth);
            checkRestaurantDebts(restaurantList);
            enterSalaryToClients(clientList);
            return actualMonth==1;
        }
        return false;
    }

    private void checkRestaurantDebts(List<Restaurant> restaurantList) {
        restaurantList.forEach(Restaurant::payDebts);
        WorkerPayrollThread.payWorkers(restaurantList);
    }

    private void enterSalaryToClients(List<Client> clientList) {
        BudgetRestartThread.restartBudgets(clientList);
    }

    public void initialize() {
        System.out.println("New Month: " + actualMonth);
        for (int i = 0; i < yearMonths.length; i++) {
            yearMonths[i] = new Month();
            yearMonths[i].initialize();
        }
    }

    public static int getActualMonth() {
        return actualMonth;
    }

    public static int getActualDay(){
        return Month.getActualDay();
    }
}
