package backend.model.simulation;

import backend.implementations.database.SQLite.controllers.SQLiteTableDeleter;
import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.implementations.routine.strategy.BestRoutineStrategy;
import backend.implementations.routine.strategy.RoutineStrategy;
import backend.model.bill.generator.XMLBill;
import backend.model.event.EventController;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.company.provider.Product;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulables.person.worker.jobSearcher.AlwaysAcceptStrategy;
import backend.model.simulables.person.worker.jobSearcher.SelectOfferStrategy;
import backend.model.simulation.settings.settingsList.GeneralSettings;
import backend.utils.DatabaseUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Simulation {

    private static List<Restaurant> restaurantList = new LinkedList<>();
    private static List<Provider> providerList = new LinkedList<>();
    private static List<Client> clientList = new LinkedList<>();
    private static List<Worker> workerList = new LinkedList<>();
    private static List<XMLBill> billList = new LinkedList<>();

    public static final SelectOfferStrategy SEARCHER_STRATEGY = new AlwaysAcceptStrategy();
    public static final RoutineStrategy ROUTINE_STRATEGY = new BestRoutineStrategy();


    public static int getProviderSize() {
        return providerList.size();
    }

    public static int getRestaurantSize() {
        return restaurantList.size();
    }

    public static int getClientSize() {
        return clientList.size();
    }

    public static int getWorkerSize() {
        return workerList.size();
    }

    public static int getPersonSize() {
        return clientList.size()+workerList.size();
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

    public static List<Worker> getWorkerList(int page) {
        int from = getFrom(page);
        int to = getTo(from,workerList.size());
        return workerList.subList(from, to);
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

    public static List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public static List<Provider> getProviderList() {
        return providerList;
    }

    public static List<Provider> getProviderList(Product product) {
        return providerList.stream()
                .filter(provider -> provider.getProduct().equals(product))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Client> getClientList() {
        return clientList;
    }

    public static List<Worker> getWorkerList() {
        return workerList;
    }

    public static List<Worker> getWorkerList(Job job) {
        return workerList.stream()
                .filter(worker -> worker.getJob().equals(job.toString()))
                .collect(Collectors.toCollection(LinkedList::new));
    }


    public static void addBill(XMLBill bill){
        if(billList.size()<DatabaseUtils.getListLimit())billList.add(bill);
    }


    public static List<Simulable> init(){
        reset();
        initElements();
        return Initializer.init();
    }

    private static void initElements() {
        try {
            providerList = Initializer.getProviders(GeneralSettings.getProviderCount());
            workerList = Initializer.getWorkers(GeneralSettings.getWorkerCount());
            restaurantList = Initializer.getRestaurants(GeneralSettings.getRestaurantCount());
            clientList = Initializer.getClients(GeneralSettings.getClientCount());
        } catch (SQLException | ClassNotFoundException e) {
            Simulator.waitForDatabaseOrThread();
            initElements();
        }
    }

    private static void reset(){
        resetBills();
        resetEvents();
        resetBank();
    }

    private static void resetBank() {
        Bank.reset();
    }

    private static void resetBills(){
        billList = new LinkedList<>();
        try {
            if(new SQLiteTableSelector().readCount("Bill")==0)return;
            new SQLiteTableDeleter().deleteAll("Bill");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database is locked, could not delete Bills of last Simulation");
        }
    }

    private static void resetEvents() {
        EventController.resetEvents();
    }

    public static List<Worker> getUnemployedWorkers(Job job){
        return Simulation.getWorkerList(job).stream()
                .filter(worker -> !worker.isWorking())
                .filter(Worker::isNotRetired)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Worker> getEmployedWorkers() {
        return workerList.stream().filter(Worker::isWorking).collect(Collectors.toCollection(LinkedList::new));
    }

    public static Simulable addWorker() {
        Worker worker = Initializer.getWorker();
        if(worker == null) return null;
        workerList.add(worker);
        return worker;
    }

    public static Simulable addClient() {
        Client client = Initializer.getClient();
        if(client == null) return null;
        clientList.add(client);
        return client;
    }

    public static Simulable addRestaurant() {
        Restaurant restaurant = Initializer.getRestaurant();
        if(restaurant == null) return null;
        restaurantList.add(restaurant);
        return restaurant;
    }

    public static Simulable addProvider() {
        Provider provider = Initializer.getProvider();
        if(provider == null) return null;
        providerList.add(provider);
        return provider;
    }
}
