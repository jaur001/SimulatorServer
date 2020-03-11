package backend.server.commands;

import backend.implementations.loaders.CSV.ClientLoaderCSV;

import backend.implementations.loaders.CSV.ProviderLoaderCSV;
import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.implementations.xmlBills.CFDIBillGenerator;
import backend.implementations.xmlBills.CFDIPayrollGenerator;
import backend.model.client.Client;
import backend.model.provider.Provider;
import backend.model.restaurant.Restaurant;
import backend.server.frontcontroller.FrontCommand;
import backend.model.threads.initializers.RoutineThread;
import backend.model.threads.initializers.WorkerThread;
import backend.model.threads.initializers.provider.ProductInitializerThread;
import backend.model.threads.initializers.provider.ProvidingThread;
import backend.model.time.Time;

import java.util.List;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        int count = 5;
        int clientRowNumber = 1;
        int providerRowNumber = 100;
        int restaurantRowNumber = 30;
        String urlClient = context.getRealPath("/CSVFiles/Clients.csv");
        String urlProvider = context.getRealPath("/CSVFiles/Providers.csv");

        SQLiteRestaurantReader.setSQLiteUrl("jdbc:sqlite:" + context.getRealPath("/Simulator.db"));

        CFDIBillGenerator.setUrl(context.getRealPath("/xmlFiles/EatingBills")+"/");
        System.out.println(context.getRealPath("/xmlFiles/EatingBills/"));
        CFDIPayrollGenerator.setUrl(context.getRealPath("/xmlFiles/Payrolls")+"/");

        List<Provider> providerList = new ProviderLoaderCSV().load(urlProvider,providerRowNumber);
        ProductInitializerThread.initProducts(providerList);

        //List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        List<Restaurant> restaurantList = new SQLiteRestaurantReader().read(restaurantRowNumber);
        WorkerThread.addWorkers(restaurantList);
        ProvidingThread.initRestaurantProviders(providerList,restaurantList);

        List<Client> clientList = new ClientLoaderCSV().load(urlClient,clientRowNumber);
        RoutineThread.setClientRoutines(clientList,restaurantList);

        executeSimulation(restaurantList,clientList,providerList);
        forward("/clients.jsp");
    }

    private void executeSimulation(List<Restaurant> restaurantList,List<Client> clientList, List<Provider> providerList) {
        Time time = new Time(restaurantList,clientList,providerList);
        while(true){
            time.play();
            for (Client i : clientList) {
                i.printRoutines();
            }
        }
    }
}
