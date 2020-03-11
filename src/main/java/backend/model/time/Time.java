package backend.model.time;

import backend.model.client.Client;
import backend.model.provider.Provider;
import backend.model.restaurant.Restaurant;

import java.util.Date;
import java.util.List;

public class Time{
    private static Year year = new Year();
    private static List<Restaurant> restaurantList;
    private static List<Client> clientList;
    private static List<Provider> providerList;
    private static int actualYear = 2020;

    public Time(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList) {
        Time.restaurantList = restaurantList;
        Time.clientList = clientList;
        Time.providerList = providerList;
        System.out.println("New Year: " + actualYear);
        year.initialize();
    }

    public static Date getActualDate(){
        return new Date(actualYear, Year.getActualMonth(), Year.getActualDay());
    }

    public void play(){
        if(year.passTime(restaurantList,clientList,providerList)){
            actualYear++;
            System.out.println("New Year: " + actualYear);
        }
    }
}
