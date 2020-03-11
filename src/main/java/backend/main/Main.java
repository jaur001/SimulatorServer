package backend.main;

import backend.implementations.loaders.CSV.ClientLoaderCSV;
import backend.implementations.loaders.CSV.ProviderLoaderCSV;
import backend.implementations.loaders.restaurant.SQLite.SQLRestaurantWriter;
import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.implementations.xmlBills.CFDIBillGenerator;
import backend.implementations.xmlBills.CFDIPayrollGenerator;
import backend.model.client.Client;
import backend.model.provider.Provider;
import backend.model.threads.initializers.RoutineThread;
import backend.model.threads.initializers.WorkerThread;
import backend.model.restaurant.Restaurant;
import backend.model.threads.initializers.provider.ProductInitializerThread;
import backend.model.threads.initializers.provider.ProvidingThread;
import backend.model.time.Time;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int count = 5;
        int clientRowNumber = 1;
        int providerRowNumber = 100;
        int restaurantRowNumber = 30;
        String urlClient = "./CSVFiles/Clients.csv";
        String urlProvider = "./CSVFiles/Providers.csv";

        CFDIBillGenerator.setUrl("./xmlFiles/EatingBills/");
        CFDIPayrollGenerator.setUrl("./xmlFiles/Payrolls/");

        String url = "jdbc:sqlite:Simulator.db";
        SQLRestaurantWriter.setSQLiteUrl(url);
        SQLiteRestaurantReader.setSQLiteUrl(url);

        List<Provider> providerList = new ProviderLoaderCSV().load(urlProvider,providerRowNumber);
        ProductInitializerThread.initProducts(providerList);

        //List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        List<Restaurant> restaurantList = new SQLiteRestaurantReader().read(restaurantRowNumber);
        WorkerThread.addWorkers(restaurantList);
        ProvidingThread.initRestaurantProviders(providerList,restaurantList);

        List<Client> clientList = new ClientLoaderCSV().load(urlClient,clientRowNumber);
        RoutineThread.setClientRoutines(clientList,restaurantList);

        Time time = new Time(restaurantList,clientList,providerList);
        while(true){
            time.play();
            for (Client client : clientList) {
                client.printRoutines();
            }
        }

    }
}
