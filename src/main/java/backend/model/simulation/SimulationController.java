package backend.model.simulation;

import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.LinkedList;
import java.util.List;

public class SimulationController extends EventGenerator {

    private List<Simulable> simulableList;
    private List<Simulable> addingList;
    private List<Simulable> removingList;


    public SimulationController(List<Simulable> simulableList) {
        this.simulableList = simulableList;
        this.addingList = new LinkedList<>();
        this.removingList = new LinkedList<>();
    }

    public void manageSimulation() {
        checkThereIsNewPerson();
        checkThereIsNewCompany();
        checkAddingList();
        checkRemovingList();

    }

    private void checkThereIsNewPerson() {
        checkThereIsNewWorker();
        checkThereIsNewClient();
    }

    private void checkThereIsNewWorker() {
        if(!WorkerSettings.newWorker()) return;
        addSimulable(Simulation.addWorker());
    }

    private void checkThereIsNewClient() {
        if(!ClientSettings.newClient()) return;
        addSimulable(Simulation.addClient());
    }

    private void checkThereIsNewCompany() {
        checkThereIsNewRestaurant();
        checkThereIsNewProvider();
    }

    private void checkThereIsNewRestaurant() {
        if(!RestaurantSettings.newRestaurant()) return;
        addSimulable(Simulation.addRestaurant());
    }

    private void checkThereIsNewProvider() {
        if(!ProviderSettings.newProvider()) return;
        addSimulable(Simulation.addProvider());
    }

    private void checkAddingList() {
        if(addingList.size() > 0) simulableList.addAll(addingList);
        addingList = new LinkedList<>();
    }

    private void checkRemovingList() {
        if(removingList.size() > 0) simulableList.removeAll(removingList);
        removingList = new LinkedList<>();
    }

    public void addSimulable(Simulable simulable){
        if(simulable!=null)simulableList.add(simulable);
    }

    public void addSimulableAtTheEnd(Simulable simulable){
        addingList.add(simulable);
    }

    public void removeSimulableAtTheEnd(Simulable simulable) {
        checkSimulable(simulable);
        removingList.add(simulable);
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

    public static void closeRestaurant(Restaurant company) {
        Simulation.getRestaurantList().remove(company);
        removeProviders(company);
        removeWorker(company);
    }


    private static void removeProviders(Restaurant company) {
        Simulation.getProviderList().stream()
                .filter(provider -> provider.getRestaurantList().contains(company))
                .forEach(provider -> provider.removeRestaurant(company));
    }

    private static void removeWorker(Restaurant company) {
        company.getWorkers()
                .forEach(worker -> {
                    if(WorkerSettings.isInRetireAge(worker)) company.getAdministrator().retireWorker(worker);
                    else company.getAdministrator().removeWorker(worker);
                });
    }

    public static void closeProvider(Provider company) {
        Simulation.getProviderList().remove(company);
        removeRestaurants(company);
    }

    private static void removeRestaurants(Provider company) {
        company.getRestaurantList()
                .forEach(restaurant -> restaurant.getAdministrator().removeProvider(company));
    }
}
