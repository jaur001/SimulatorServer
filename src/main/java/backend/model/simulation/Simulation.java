package backend.model.simulation;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.bill.generator.XMLBill;
import backend.model.simulables.Simulable;
import backend.model.simulables.client.Client;
import backend.model.simulables.provider.Provider;
import backend.model.simulables.restaurant.Restaurant;
import backend.utils.DatabaseUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    private static AtomicBoolean condition;
    private static AtomicBoolean restart;
    private static List<Restaurant> restaurantList = new LinkedList<>();
    private static List<Provider> providerList = new LinkedList<>();
    private static List<Client> clientList = new LinkedList<>();
    private static List<XMLBill> billList = new LinkedList<>();
    private static String uriProvider;
    private static String uriClient;
    private static int actualPage = 0;


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



    public static int geClientSize() {
        return clientList.size();
    }

    public static int getProviderSize() {
        return providerList.size();
    }

    public static int getRestaurantSize() {
        return restaurantList.size();
    }

    private static int getFrom(int page) {
        return DatabaseUtils.getPageLength()*(page-1);
    }

    private static int getTo(int from, int to) {
        return Math.min(from + DatabaseUtils.getPageLength(), to);
    }

    public static List<Restaurant> getRestaurantList(int page) {
        int from = getFrom(page);
        int to = getTo(from,restaurantList.size());
        return restaurantList.subList(from, to);
    }

    public static List<Provider> getProviderList(int page) {
        int from = getFrom(page);
        int to = getTo(from,providerList.size());
        return providerList.subList(from, to);
    }

    public static List<Client> getClientList(int page) {
        int from = getFrom(page);
        int to = getTo(from,clientList.size());
        return clientList.subList(from, to);
    }

    public static List<XMLBill> getBillList(int page) {
        int from = getFrom(page);
        int to = getTo(from,billList.size());
        if(billList.size()>(page-1)*DatabaseUtils.getPageLength())return billList.subList(from, to);
        else return getFromDatabase(page);
    }

    private static List<XMLBill> getFromDatabase(int page) {
        try {
            return new BillBuilder().buildList(new SQLiteTableSelector().read("Bill",page));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }



    public static void addBill(XMLBill bill){
        if(billList.size()<DatabaseUtils.getListLimit())billList.add(bill);
    }

    public static void removeBill(XMLBill bill){
        billList.remove(bill);
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

    public static List<Simulable> init(int providerCount, int restaurantCount, int clientCount){
        condition = new AtomicBoolean(true);
        restart = new AtomicBoolean(false);
        try {
            providerList = Initializer.getProviders(providerCount);
            restaurantList = Initializer.getRestaurants(restaurantCount);
            clientList = Initializer.getClients(clientCount);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Initializer.getSimulableList();
    }

    public static int getActualPage() {
        return actualPage;
    }

    public static void setBillPage(int page) {
        actualPage = page;
    }
}
