package backend.model.simulation.administration.initializer;

import backend.implementations.SQLite.SQLiteTableAdministrator;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.implementations.jobSelector.MostEmployedJobSelector;
import backend.initializers.WorkerThread;
import backend.initializers.secondaryCompanies.service.ServiceThread;
import backend.initializers.secondaryCompanies.service.ServicingThread;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.model.simulation.administration.centralControl.SimulationAdministrator;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.initializers.RoutineThread;
import backend.initializers.WorkerSearcherThread;
import backend.initializers.secondaryCompanies.provider.ProductThread;
import backend.initializers.secondaryCompanies.provider.ProvidingThread;
import backend.view.loaders.database.TableAdministrator;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulationInitializer {

    private static final TableAdministrator administrator = new SQLiteTableAdministrator();

    public static List<Provider> getProviders(int providerCount) throws SQLException, ClassNotFoundException {
        List<Provider> providerList = administrator.read(Simulation.getSecondaryCompanySize(),providerCount,Provider.class);
        ProductThread.initProducts(providerList);
        return providerList;
    }

    public static List<ServiceCompany> getServiceCompanies(int serviceCompanyCount) throws SQLException, ClassNotFoundException {
        List<ServiceCompany> companies = administrator.read(Simulation.getSecondaryCompanySize(),serviceCompanyCount,ServiceCompany.class);
        ServiceThread.initProducts(companies);
        return companies;
    }

    public static List<Restaurant> getRestaurants(int restaurantCount) throws SQLException, ClassNotFoundException {
        //return RestaurantThread.loadRestaurantsPage(restaurantCount/30);
        return administrator.read(Simulation.getRestaurantSize(),restaurantCount,Restaurant.class);
    }

    public static List<Client> getClients(int clientCount) throws SQLException, ClassNotFoundException {
        List<Client> clientList = administrator.read(Simulation.getClientSize(),clientCount,Client.class);
        clientList.forEach(client -> client.setSalary(ClientSettings.getSalarySample()));
        return clientList;
    }


    public static List<Worker> getWorkers(int workerCount) throws SQLException, ClassNotFoundException {
        List<Worker> workerList = administrator.read(Simulation.getWorkerSize(),workerCount,Worker.class);
        if(workerCount==1) WorkerThread.setJob(workerList.get(0),new MostEmployedJobSelector());
        else WorkerThread.setJobs(workerList);
        return workerList;
    }

    public static List<Simulable> init() {
        prepareSimulables();
        return createSimulables();
    }

    private static void prepareSimulables() {
        ProvidingThread.initRestaurantsProviders();
        ServicingThread.initProvidersWithTransport();
        ServicingThread.initRestaurantsWithCleaning();
        WorkerSearcherThread.addWorkers();
        RoutineThread.setClientRoutines();
        SimulationAdministrator.makeChanges();
    }

    private static List<Simulable> createSimulables() {
        List<Simulable> simulableList = new CopyOnWriteArrayList<>();
        simulableList.add(new Bank());
        simulableList.addAll(SimulationDataController.getSimulationData().getCompanyList());
        simulableList.addAll(SimulationDataController.getSimulationData().getClientList());
        return simulableList;
    }

    public static Worker getWorker() {
        try {
            return getWorkers(1).get(0);
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Client getClient() {
        try {
            Client client = getClients(1).get(0);
            client.setRoutineList(new RoutineList(client.getSalary(), ClientSettings.getRoutineList(client.getSalary())));
            return client;
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Restaurant getRestaurant() {
        try {
            Restaurant restaurant = getRestaurants(1).get(0);
            WorkerSearcherThread.addWorker(restaurant);
            ProvidingThread.initProvidersForRestaurant(restaurant);
            ServicingThread.initRestaurantsWithCleaning();
            return restaurant;
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Provider getProvider() {
        try {
            Provider provider = getProviders(1).get(0);
            ServicingThread.initProvidersWithTransport();
            return provider;
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ServiceCompany getService() {
        try {
            return getServiceCompanies(1).get(0);
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }
}
