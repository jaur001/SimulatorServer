package backend.model.simulation;

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
import backend.model.simulation.simulator.SimulatorCommitter;
import backend.model.simulation.simulator.Simulator;

import java.util.List;

public class SimulationController extends EventGenerator {

    private List<Simulable> simulableList;


    public SimulationController(List<Simulable> simulableList) {
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
        Simulable simulable = Simulation.addWorker();
        addSimulable(simulable);
    }

    private void checkThereIsNewClient() {
        if(!ClientSettings.newClient()) return;
        Simulable simulable = Simulation.addClient();
        addSimulable(simulable);
    }

    private void checkThereIsNewCompany() {
        checkThereIsNewRestaurant();
        checkThereIsNewProvider();
    }

    private void checkThereIsNewRestaurant() {
        if(!RestaurantSettings.newRestaurant()) return;
        Simulable simulable = Simulation.addRestaurant();
        addSimulable(simulable);
    }

    private void checkThereIsNewProvider() {
        if(!ProviderSettings.newProvider()) return;
        Simulable simulable = Simulation.addProvider();
        addSimulable(simulable);
    }

    public void addSimulable(Simulable simulable){
        if(simulable!=null)simulableList.add(simulable);
    }

    public void removeSimulable(Simulable simulable) {
        checkSimulable(simulable);
        simulableList.remove(simulable);
    }

    private void checkSimulable(Simulable simulable) {
        if(simulable instanceof Client) addEvent((Client)simulable);
        if (simulable instanceof Company) addEvent((Company)simulable);
    }

    public static void diePerson(Client client) {
        if(client instanceof Worker) Simulation.getWorkerList().remove(client);
        else Simulation.getClientList().remove(client);
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
                .forEach(worker -> SimulatorCommitter.commitRemoveWorker(restaurant,worker));
    }

    public static void closeProvider(Provider provider) {
        Simulation.getProviderList().remove(provider);
        removeRestaurants(provider);
    }

    private static void removeRestaurants(Provider provider) {
        provider.getCompanyList().stream()
                .filter(companyClient -> companyClient instanceof Restaurant)
                .map(companyClient -> (Restaurant) companyClient)
                .forEach(restaurant -> SimulatorCommitter.commitRemoveProvider(restaurant,provider));
    }
}
