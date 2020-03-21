package backend.model.simulation;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.bill.generator.XMLBill;
import backend.model.simulables.Simulable;
import backend.model.simulables.client.Client;
import backend.model.simulables.provider.Provider;
import backend.model.simulables.restaurant.Restaurant;
import backend.threadsInitializers.RoutineThread;
import backend.threadsInitializers.WorkerThread;
import backend.threadsInitializers.provider.ProductInitializerThread;
import backend.threadsInitializers.provider.ProvidingThread;
import backend.view.loaders.database.builder.builders.ClientBuilder;
import backend.view.loaders.database.builder.builders.ProviderBuilder;
import backend.view.loaders.database.builder.builders.RestaurantBuilder;
import backend.view.loaders.database.elements.Row;

import java.sql.SQLException;
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
    private static List<XMLBill> billList = new ArrayList<>();
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

    public static List<XMLBill> getBillList() {
        return billList;
    }

    public static void addBill(XMLBill bill){
        billList.add(bill);
    }

    public static void removeBill(XMLBill bill){
        billList.remove(bill);
    }



    public static List<Simulable> init(int providerCount, int restaurantCount, int clientCount){
        condition = new AtomicBoolean(true);
        restart = new AtomicBoolean(false);
        try {
            getProviders(providerCount);
            getRestaurants(restaurantCount);
            getClients(clientCount);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return getSimulableList(restaurantList,clientList);
    }

    private static List<Provider> getProviders(int providerCount) throws SQLException, ClassNotFoundException {
        providerList = new ProviderBuilder().buildList(getRows("Provider", ProviderNIFCreator.INITIAL_VALUE,providerCount));
        ProductInitializerThread.initProducts(providerList);
        return providerList;
    }

    private static List<Restaurant> getRestaurants(int restaurantCount) throws SQLException, ClassNotFoundException {
        //restaurantList = RestaurantThread.loadRestaurantsPage(restaurantCount/30);
        restaurantList = new RestaurantBuilder().buildList(getRows("Restaurant",RestaurantNIFCreator.INITIAL_VALUE,restaurantCount));
        WorkerThread.addWorkers(restaurantList);
        ProvidingThread.initRestaurantsProviders(providerList,restaurantList);
        return restaurantList;
    }

    private static List<Row> getRows(String tableName, int initialValue, int restaurantCount) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(tableName, initialValue, initialValue+restaurantCount);
    }

    private static List<Client> getClients(int clientCount) throws SQLException, ClassNotFoundException {
        clientList = new ClientBuilder().buildList(getRows("Client", PersonNIFCreator.INITIAL_VALUE,clientCount));
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
