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
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    private static AtomicBoolean condition;
    private static AtomicBoolean restart;
    private static List<Restaurant> restaurantList = new ArrayList<>();
    private static List<Provider> providerList = new ArrayList<>();
    private static List<Client> clientList = new ArrayList<>();
    private static String uriProvider;
    private static String uriClient;

    public static void restart(){
        restart.set(true);
    }

    public static void changeExecuting() {
        if(condition.get()) condition.set(false);
        else condition.set(true);
    }

    public static boolean isInitialized(){
        return condition != null;
    }

    public static String getUriProvider() {
        return uriProvider;
    }

    public static void setUriProvider(String uriProvider) {
        Simulation.uriProvider = uriProvider;
    }

    public static String getUriClient() {
        return uriClient;
    }

    public static void setUriClient(String uriClient) {
        Simulation.uriClient = uriClient;
    }

    public static List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public static List<Provider> getProviderList() {
        return providerList;
    }

    public static List<Client> getClientList() {
        return clientList;
    }

    public static List<Simulable> init(int providerCount, int restaurantCount, int clientCount){
        condition = new AtomicBoolean(true);
        restart = new AtomicBoolean(false);
        getProviders(providerCount);
        getRestaurants(restaurantCount);
        getClients(clientCount);
        return getSimulableList(restaurantList,clientList);
    }

    private static List<Provider> getProviders(int providerCount) {
        providerList = new CSVProviderLoader(uriProvider).load(providerCount);
        ProductInitializerThread.initProducts(providerList);
        return providerList;
    }

    private static List<Restaurant> getRestaurants(int restaurantCount) {
        //restaurantList = RestaurantThread.loadRestaurantsPage(restaurantCount/30);
        restaurantList = new SQLiteRestaurantReader().read(restaurantCount);
        WorkerThread.addWorkers(restaurantList);
        ProvidingThread.initRestaurantsProviders(providerList,restaurantList);
        return restaurantList;
    }

    private static List<Client> getClients(int clientCount) {
        clientList = new CSVClientLoader(uriClient).load(clientCount);
        RoutineThread.setClientRoutines(clientList,restaurantList);
        return clientList;
    }

    private static List<Simulable> getSimulableList(List<Restaurant> restaurantList, List<Client> clientList) {
        List<Simulable> simulableList = new ArrayList<>();
        simulableList.addAll(restaurantList);
        simulableList.addAll(clientList);
        return simulableList;
    }

    public static void execute(int providerCount, int restaurantCount, int clientCount) {
        TimeLine timeLine = new TimeLine(Simulation.init(providerCount,restaurantCount,clientCount));
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            while (!restart.get()){
                if(condition.get()) timeLine.play();
            }
        });

    }
}
