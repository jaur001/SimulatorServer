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

public class Initializer {

    private static List<Restaurant> restaurantList = new LinkedList<>();
    private static List<Provider> providerList = new LinkedList<>();
    private static List<Client> clientList = new LinkedList<>();
    private static List<Worker> workerList = new LinkedList<>();


    public static List<Provider> getProviders(int providerCount) throws SQLException, ClassNotFoundException {
        providerList = new ProviderBuilder().buildList(getRows("Provider", ProviderNIFCreator.getInitialValue(),providerCount));
        ProductInitializerThread.initProducts(providerList);
        return providerList;
    }

    public static List<Worker> getWorkers(int workerCount) throws SQLException, ClassNotFoundException {
        workerList = new WorkerBuilder().buildList(getRows("Person", PersonNIFCreator.getInitialValue()+ ClientSettings.getLimit(),workerCount));
        WorkerThread.setJobs(workerList);
        return workerList;
    }

    public static List<Restaurant> getRestaurants(int restaurantCount) throws SQLException, ClassNotFoundException {
        //restaurantList = RestaurantThread.loadRestaurantsPage(restaurantCount/30);
        restaurantList = new RestaurantBuilder().buildList(getRows("Restaurant", RestaurantNIFCreator.getInitialValue(),restaurantCount));
        WorkerSearcherThread.addWorkers(restaurantList);
        ProvidingThread.initRestaurantsProviders(providerList,restaurantList);
        return restaurantList;
    }

    public static List<Client> getClients(int clientCount) throws SQLException, ClassNotFoundException {
        clientList = new ClientBuilder().buildList(getRows("Person", PersonNIFCreator.getInitialValue(),clientCount));
        RoutineThread.setClientRoutines(clientList,restaurantList);
        return clientList;
    }

    public static List<Simulable> getSimulableList() {
        restaurantList.forEach(Bank::addPayer);
        clientList.forEach(Bank::addCollector);
        List<Simulable> simulableList = new LinkedList<>();
        simulableList.add(new Bank());
        simulableList.addAll(restaurantList);
        simulableList.addAll(clientList);
        simulableList.addAll(workerList);
        return simulableList;
    }

    private static List<Row> getRows(String tableName, int initialValue, int count) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(tableName, initialValue, initialValue+count);
    }
}
