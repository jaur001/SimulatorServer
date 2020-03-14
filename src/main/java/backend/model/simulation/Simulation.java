package backend.model.simulation;

import backend.implementations.loaders.CSV.CSVClientLoader;
import backend.implementations.loaders.CSV.CSVProviderLoader;
import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.model.simulables.Simulable;
import backend.model.simulables.client.Client;
import backend.model.simulables.provider.Provider;
import backend.model.simulables.restaurant.Restaurant;
import backend.threads.initializers.RoutineThread;
import backend.threads.initializers.WorkerThread;
import backend.threads.initializers.provider.ProductInitializerThread;
import backend.threads.initializers.provider.ProvidingThread;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    public static List<Simulable> init(int providerCount, int restaurantCount, int clientCount, String urlClient, String urlProvider){

        List<Restaurant> restaurantList = getRestaurants(restaurantCount, getProviders(providerCount, urlProvider));

        return getSimulableList(restaurantList,getClients(clientCount, urlClient, restaurantList));
    }

    private static List<Provider> getProviders(int providerCount, String urlProvider) {
        List<Provider> providerList = new CSVProviderLoader(urlProvider).load(providerCount);
        ProductInitializerThread.initProducts(providerList);
        return providerList;
    }

    private static List<Restaurant> getRestaurants(int restaurantCount, List<Provider> providerList) {
        //List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(restaurantCount/30);
        List<Restaurant> restaurantList = new SQLiteRestaurantReader().read(restaurantCount);
        WorkerThread.addWorkers(restaurantList);
        ProvidingThread.initRestaurantProviders(providerList,restaurantList);
        return restaurantList;
    }

    private static List<Client> getClients(int clientCount, String urlClient, List<Restaurant> restaurantList) {
        List<Client> clientList = new CSVClientLoader(urlClient).load(clientCount);
        RoutineThread.setClientRoutines(clientList,restaurantList);
        return clientList;
    }

    private static List<Simulable> getSimulableList(List<Restaurant> restaurantList, List<Client> clientList) {
        List<Simulable> simulableList = new ArrayList<>();
        simulableList.addAll(restaurantList);
        simulableList.addAll(clientList);
        return simulableList;
    }

    public static void execute(TimeLine timeLine) {
        while (true){
            timeLine.play();
        }
    }
}
