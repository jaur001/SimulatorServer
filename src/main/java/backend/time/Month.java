package backend.time;

import backend.model.client.Client;
import backend.model.provider.Provider;
import backend.model.restaurant.Restaurant;
import backend.threads.bills.RoutineCheckerThread;

import java.util.List;

public class Month {
    private Day[] monthDays = new Day[30];
    private static int actualDay = 1;
    private int lastDay = 30;


    public boolean passTime(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList) {
        if(monthDays[actualDay-1].passTime(restaurantList,clientList,providerList)){
            actualDay = ++actualDay>lastDay?1 : actualDay;
            System.out.println("New Day: " + actualDay);
            receiveClients(clientList);
            return actualDay==1;
        }
        return false;
    }


    private void receiveClients(List<Client> clientList) {
        RoutineCheckerThread.checkRoutines(clientList);
    }

    public void initialize() {
        for (int i = 0; i < monthDays.length; i++) {
            monthDays[i] = new Day();
            monthDays[i].initialize();
        }
    }

    public static int getActualDay() {
        return actualDay;
    }
}
