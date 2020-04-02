package backend.model.simulation;

import backend.implementations.database.SQLite.controllers.SQLiteTableDeleter;
import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.bill.generator.XMLBill;
import backend.model.simulables.Simulable;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.provider.Provider;
import backend.model.simulables.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.GeneralSettings;
import backend.utils.DatabaseUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Simulation {

    private static AtomicBoolean executing;
    private static AtomicBoolean restart;

    private static List<Restaurant> restaurantList = new LinkedList<>();
    private static List<Provider> providerList = new LinkedList<>();
    private static List<Client> clientList = new LinkedList<>();
    private static List<Worker> workerList = new LinkedList<>();
    private static List<XMLBill> billList = new LinkedList<>();


    private static String uriProvider;
    private static String uriClient;



    public static void restart(){
        restart.set(true);
        executing = null;
    }

    public static void changeExecuting() {
        if(executing.get()) executing.set(false);
        else executing.set(true);
    }

    public static boolean isInitialized(){
        return executing != null;
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

    public static List<Worker> getWorkerList(Job job) {
        return workerList.stream()
                .filter(worker -> worker.getJob().equals(job.toString()))
                .collect(Collectors.toCollection(LinkedList::new));
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

    public static void resetBills(){
        billList = new LinkedList<>();
    }

    public static void startStop(){
        if(!Simulation.isInitialized()) Simulation.execute();
        else Simulation.changeExecuting();
    }

    private static void execute() {
        TimeLine timeLine = new TimeLine(Simulation.init());
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
        restaurantList.forEach(restaurant -> System.out.println(restaurant.getNumberOfWorkers()));
        executor.submit(() -> {
            while (!restart.get()){
                if(executing.get()){
                    timeLine.play();
                }
            }
        });
    }

    private static List<Simulable> init(){
        executing = new AtomicBoolean(true);
        restart = new AtomicBoolean(false);
        try {
            reset();
            providerList = Initializer.getProviders(GeneralSettings.getProviderCount());
            workerList = Initializer.getWorkers(GeneralSettings.getWorkerCount());
            restaurantList = Initializer.getRestaurants(GeneralSettings.getRestaurantCount());
            clientList = Initializer.getClients(GeneralSettings.getClientCount());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Initializer.getSimulableList();
    }

    private static void reset(){
        resetBills();
        try {
            new SQLiteTableDeleter().deleteAll("Bill");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
