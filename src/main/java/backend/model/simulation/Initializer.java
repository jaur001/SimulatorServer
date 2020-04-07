package backend.model.simulation;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.initializers.WorkerThread;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.initializers.RoutineThread;
import backend.initializers.WorkerSearcherThread;
import backend.initializers.provider.ProductInitializerThread;
import backend.initializers.provider.ProvidingThread;
import backend.view.loaders.database.builder.builders.ClientBuilder;
import backend.view.loaders.database.builder.builders.ProviderBuilder;
import backend.view.loaders.database.builder.builders.RestaurantBuilder;
import backend.view.loaders.database.builder.builders.WorkerBuilder;
import backend.view.loaders.database.elements.Row;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Initializer {

    public static List<Provider> getProviders(int providerCount) throws SQLException, ClassNotFoundException {
        List<Provider> providerList = new ProviderBuilder().buildList(getRows("Provider", ProviderNIFCreator.getInitialValue(), providerCount));
        ProductInitializerThread.initProducts(providerList);
        return providerList;
    }

    public static List<Worker> getWorkers(int workerCount) throws SQLException, ClassNotFoundException {
        List<Worker> workerList = new WorkerBuilder().buildList(getRows("Person", PersonNIFCreator.getInitialValue() + ClientSettings.getLimit(), workerCount));
        WorkerThread.setJobs(workerList);
        return workerList;
    }

    public static List<Restaurant> getRestaurants(int restaurantCount) throws SQLException, ClassNotFoundException {
        //return RestaurantThread.loadRestaurantsPage(restaurantCount/30);
        return new RestaurantBuilder().buildList(getRows("Restaurant", RestaurantNIFCreator.getInitialValue(),restaurantCount));
    }

    public static List<Client> getClients(int clientCount) throws SQLException, ClassNotFoundException {
        return  new ClientBuilder().buildList(getRows("Person", PersonNIFCreator.getInitialValue(),clientCount));
    }

    public static List<Simulable> init() {
        prepareSimulables();
        addPayersAndCollectors();
        return createSimulables();
    }

    private static void prepareSimulables() {
        ProvidingThread.initRestaurantsProviders();
        WorkerSearcherThread.addWorkers();
        RoutineThread.setClientRoutines();
    }

    private static void addPayersAndCollectors() {
        Simulation.getRestaurantList().forEach(Bank::addPayer);
        Simulation.getProviderList().forEach(Bank::addPayer);
        Simulation.getClientList().forEach(Bank::addCollector);
    }

    private static List<Simulable> createSimulables() {
        List<Simulable> simulableList = new LinkedList<>();
        simulableList.add(new Bank());
        simulableList.addAll(Simulation.getRestaurantList());
        simulableList.addAll(Simulation.getClientList());
        simulableList.addAll(Simulation.getWorkerList());
        simulableList.addAll(Simulation.getProviderList());
        return simulableList;
    }

    private static List<Row> getRows(String tableName, int initialValue, int count) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(tableName, initialValue, initialValue+count);
    }

    public static Worker getWorker() {
        try {
            Worker worker = getWorkers(1).get(0);
            WorkerThread.setJob(worker);
            return worker;
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }
}
