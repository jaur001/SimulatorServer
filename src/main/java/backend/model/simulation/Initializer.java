package backend.model.simulation;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.NIFCreator.ProviderNIFCreator;
import backend.model.NIFCreator.RestaurantNIFCreator;
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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Initializer {

    private static List<Provider> providerList;
    private static List<Restaurant> restaurantList;
    private static List<Client> clientList;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("In shutdown hook"), "Shutdown-thread"));
    }

    public static List<Provider> getProviders(int providerCount) throws SQLException, ClassNotFoundException {
        providerList = new ProviderBuilder().buildList(getRows("Provider", ProviderNIFCreator.getInitialValue(),providerCount));
        ProductInitializerThread.initProducts(providerList);
        return providerList;
    }

    public static List<Restaurant> getRestaurants(int restaurantCount) throws SQLException, ClassNotFoundException {
        //restaurantList = RestaurantThread.loadRestaurantsPage(restaurantCount/30);
        restaurantList = new RestaurantBuilder().buildList(getRows("Restaurant", RestaurantNIFCreator.getInitialValue(),restaurantCount));
        WorkerThread.addWorkers(restaurantList);
        ProvidingThread.initRestaurantsProviders(providerList,restaurantList);
        return restaurantList;
    }

    private static List<Row> getRows(String tableName, int initialValue, int restaurantCount) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(tableName, initialValue, initialValue+restaurantCount);
    }

    public static List<Client> getClients(int clientCount) throws SQLException, ClassNotFoundException {
        clientList = new ClientBuilder().buildList(getRows("Client", PersonNIFCreator.getInitialValue(),clientCount));
        RoutineThread.setClientRoutines(clientList,restaurantList);
        return clientList;
    }

    public static List<Simulable> getSimulableList() {
        List<Simulable> simulableList = new LinkedList<>();
        simulableList.addAll(restaurantList);
        simulableList.addAll(clientList);
        return simulableList;
    }
}
