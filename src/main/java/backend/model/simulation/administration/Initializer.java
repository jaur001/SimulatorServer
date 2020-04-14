package backend.model.simulation.administration;

import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.initializers.WorkerThread;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.initializers.RoutineThread;
import backend.initializers.WorkerSearcherThread;
import backend.initializers.provider.ProductInitializerThread;
import backend.initializers.provider.ProvidingThread;
import backend.model.simulation.simulator.Simulator;
import backend.view.loaders.database.builder.builders.ClientBuilder;
import backend.view.loaders.database.builder.builders.ProviderBuilder;
import backend.view.loaders.database.builder.builders.RestaurantBuilder;
import backend.view.loaders.database.builder.builders.WorkerBuilder;
import backend.view.loaders.database.elements.Row;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Initializer {

    public static List<Provider> getProviders(int providerCount) throws SQLException, ClassNotFoundException {
        List<Provider> providerList = new ProviderBuilder().buildList(getRows("Provider", ProviderNIFCreator.getInitialValue()+Simulation.getProviderSize(), providerCount));
        ProductInitializerThread.initProducts(providerList);
        return providerList;
    }

    public static List<Restaurant> getRestaurants(int restaurantCount) throws SQLException, ClassNotFoundException {
        //return RestaurantThread.loadRestaurantsPage(restaurantCount/30);
        return new RestaurantBuilder().buildList(getRows("Restaurant", RestaurantNIFCreator.getInitialValue()+Simulation.getRestaurantSize(),restaurantCount));
    }

    public static List<Client> getClients(int clientCount) throws SQLException, ClassNotFoundException {
        List<Client> clientList = new ClientBuilder().buildList(getRows("Person", PersonNIFCreator.getInitialValue() + Simulation.getPersonSize(), clientCount));
        clientList.forEach(client -> client.setSalary(ClientSettings.getSalarySample()));
        return clientList;
    }

    public static List<Worker> getWorkers(int workerCount) throws SQLException, ClassNotFoundException {
        List<Worker> workerList = new WorkerBuilder().buildList(getRows("Person", PersonNIFCreator.getInitialValue() + Simulation.getPersonSize(), workerCount));
        WorkerThread.setJobs(workerList);
        return workerList;
    }

    public static List<Simulable> init() {
        prepareSimulables();
        return createSimulables();
    }

    private static void prepareSimulables() {
        ProvidingThread.initRestaurantsProviders();
        WorkerSearcherThread.addWorkers();
        RoutineThread.setClientRoutines();
        Simulator.makeChanges();
    }

    private static List<Simulable> createSimulables() {
        List<Simulable> simulableList = new CopyOnWriteArrayList<>();
        simulableList.add(new Bank());
        simulableList.addAll(Simulation.getProviderList());
        simulableList.addAll(Simulation.getRestaurantList());
        simulableList.addAll(Simulation.getClientList());
        return simulableList;
    }

    private static List<Row> getRows(String tableName, int initialValue, int count) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(tableName, initialValue, initialValue+count);
    }

    public static Worker getWorker() {
        try {
            return getWorkers(1).get(0);
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static Client getClient() {
        try {
            Client client = getClients(1).get(0);
            client.setRoutineList(new RoutineList(client.getSalary(), ClientSettings.getRoutineList(client.getSalary())));
            return client;
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static Restaurant getRestaurant() {
        try {
            Restaurant restaurant = getRestaurants(1).get(0);
            WorkerSearcherThread.addWorker(restaurant);
            ProvidingThread.initProvidersForRestaurant(restaurant);
            return restaurant;
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Provider getProvider() {
        try {
            return getProviders(1).get(0);
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }
}
