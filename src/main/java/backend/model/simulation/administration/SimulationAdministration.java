package backend.model.simulation.administration;

import backend.model.event.EventController;
import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;
import backend.model.simulation.simulator.Simulator;

import java.util.List;

public class SimulationAdministration extends EventGenerator {

    private List<Simulable> simulableList;


    public SimulationAdministration(List<Simulable> simulableList) {
        this.simulableList = simulableList;
    }

    public void manageSimulation() {
        Simulator.makeChanges();
        checkThereIsNewPerson();
        checkThereIsNewCompany();
    }

    private void checkThereIsNewPerson() {
        checkThereIsNewWorker();
        checkThereIsNewClient();
    }

    private void checkThereIsNewWorker() {
        if(!WorkerSettings.newWorker()) return;
        Worker simulable = (Worker) addWorker();
        if(simulable!=null)
            EventController.addEvent(simulable.getFullName() + " has entered to the simulation.");
        addSimulable(simulable);
    }

    private void checkThereIsNewClient() {
        if(!ClientSettings.newClient()) return;
        Simulable simulable = addClient();
        addSimulable(simulable);
    }

    private void checkThereIsNewCompany() {
        checkThereIsNewRestaurant();
        checkThereIsNewProvider();
    }

    private void checkThereIsNewRestaurant() {
        if(!RestaurantSettings.newRestaurant()) return;
        Simulable simulable = addRestaurant();
        addSimulable(simulable);
    }

    private void checkThereIsNewProvider() {
        if(!ProviderSettings.newProvider()) return;
        Simulable simulable = addProvider();
        addSimulable(simulable);
    }

    public void addSimulable(Simulable simulable){
        if(simulable==null) return;
        if(!(simulable instanceof Worker))addEvent(simulable);
        simulableList.add(simulable);
    }

    public void removeSimulable(Simulable simulable) {
        addEvent(simulable);
        simulableList.remove(simulable);
    }

    public static Simulable addWorker() {
        Worker worker = Initializer.getWorker();
        if(worker == null) return null;
        Simulator.makeChanges();
        Simulation.getWorkerList().add(worker);
        Simulation.getClientList().add(worker);
        return worker;
    }

    public static Simulable addClient() {
        Client client = Initializer.getClient();
        if(client == null) return null;
        Simulator.makeChanges();
        Simulation.getClientList().add(client);
        return client;
    }

    public static Simulable addRestaurant() {
        Restaurant restaurant = Initializer.getRestaurant();
        if(restaurant == null) return null;
        Simulator.makeChanges();
        if(restaurant.getNumberOfWorkers()==0) return null;
        Simulation.getRestaurantList().add(restaurant);
        return restaurant;
    }

    public static Simulable addProvider() {
        Provider provider = Initializer.getProvider();
        Simulator.makeChanges();
        if(provider == null) return null;
        Simulation.getProviderList().add(provider);
        return provider;
    }

    public static void diePerson(Client client) {
        if(client instanceof Worker) Simulation.getWorkerList().remove(client);
        Simulation.getClientList().remove(client);
    }

    public static void closeCompany(Company company) {
        if(company instanceof Restaurant) closeRestaurant((Restaurant)company);
        else if(company instanceof Provider) closeProvider((Provider)company);
    }

    public static void closeRestaurant(Restaurant restaurant) {
        Simulation.getRestaurantList().remove(restaurant);
        removeProviders(restaurant);
        removeWorkers(restaurant);
    }

    private static void removeProviders(Restaurant restaurant) {
        restaurant.getAdministrator().getProvidersList()
                .forEach(provider -> provider.removeClient(restaurant));
    }

    private static void removeWorkers(Restaurant restaurant) {
        restaurant.getWorkers()
                .forEach(worker -> SimulationCommitter.commitRemoveWorker(restaurant,worker));
    }

    public static void closeProvider(Provider provider) {
        Simulation.getProviderList().remove(provider);
        removeRestaurants(provider);
    }

    private static void removeRestaurants(Provider provider) {
        provider.getCompanyList().stream()
                .filter(companyClient -> companyClient instanceof Restaurant)
                .map(companyClient -> (Restaurant) companyClient)
                .forEach(restaurant -> SimulationCommitter.commitRemoveProvider(restaurant,provider));
    }
}
