package backend.model.simulation.administration;

import backend.model.event.EventController;
import backend.model.event.EventGenerator;
import backend.model.event.events.client.DeadClientEvent;
import backend.model.event.events.client.NewClientEvent;
import backend.model.event.events.company.ClosedCompanyEvent;
import backend.model.event.events.company.NewCompanyEvent;
import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.List;

public class SimulationAdministrator extends EventGenerator {

    private List<Simulable> simulableList;
    private SimulationCommitter committer;

    public SimulationAdministrator(List<Simulable> simulableList, SimulationCommitter committer) {
        this.simulableList = simulableList;
        this.committer = committer;
    }

    public void manageSimulation() {
        Simulator.makeChanges();
        checkThereIsNewPerson();
        checkThereIsNewCompany();
        EventController.resizeList();
    }

    private void checkThereIsNewPerson() {
        checkThereIsNewWorker();
        checkThereIsNewClient();
    }

    private void checkThereIsNewWorker() {
        if(!WorkerSettings.newWorker()) return;
        Simulable simulable = addWorker();
        if(simulable != null)addEvent(new NewClientEvent((Client)simulable));
        addSimulable(simulable);
    }

    private void checkThereIsNewClient() {
        if(!ClientSettings.newClient()) return;
        Simulable simulable = addClient();
        if(simulable != null)addEvent(new NewClientEvent((Client)simulable));
        addSimulable(simulable);
    }

    private void checkThereIsNewCompany() {
        checkThereIsNewRestaurant();
        checkThereIsNewProvider();
    }

    private void checkThereIsNewRestaurant() {
        if(!RestaurantSettings.newRestaurant()) return;
        Simulable simulable = addRestaurant();
        if(simulable != null)addEvent(new NewCompanyEvent((Company)simulable));
        addSimulable(simulable);
    }

    private void checkThereIsNewProvider() {
        if(!ProviderSettings.newProvider()) return;
        Simulable simulable = addProvider();
        if(simulable != null)addEvent(new NewCompanyEvent((Company)simulable));
        addSimulable(simulable);
    }

    public void addSimulable(Simulable simulable){
        if(simulable!=null) simulableList.add(simulable);
    }

    public void removeSimulable(Simulable simulable) {
        simulableList.remove(simulable);
    }

    private Simulable addWorker() {
        Worker worker = Initializer.getWorker();
        if(worker == null) return null;
        Simulator.makeChanges();
        Simulation.getWorkerList().add(worker);
        Simulation.getClientList().add(worker);
        return worker;
    }

    private Simulable addClient() {
        Client client = Initializer.getClient();
        if(client == null) return null;
        Simulator.makeChanges();
        Simulation.getClientList().add(client);
        return client;
    }

    private Simulable addRestaurant() {
        Restaurant restaurant = Initializer.getRestaurant();
        if(restaurant == null) return null;
        Simulator.makeChanges();
        if(restaurant.getNumberOfWorkers()==0) return null;
        Simulation.getRestaurantList().add(restaurant);
        return restaurant;
    }

    private Simulable addProvider() {
        Provider provider = Initializer.getProvider();
        Simulator.makeChanges();
        if(provider == null) return null;
        Simulation.getProviderList().add(provider);
        return provider;
    }

    public void diePerson(Client client) {
        if(client instanceof Worker) Simulation.getWorkerList().remove(client);
        Simulation.getClientList().remove(client);
        removeSimulable(client);
        addEvent(new DeadClientEvent(client));
    }

    public void closeCompany(Company company) {
        if(company instanceof Restaurant) closeRestaurant((Restaurant)company);
        else if(company instanceof Provider) closeProvider((Provider)company);
        removeSimulable(company);
        addEvent(new ClosedCompanyEvent(company));
    }

    private void closeRestaurant(Restaurant restaurant) {
        Simulation.getRestaurantList().remove(restaurant);
        removeProviders(restaurant);
        removeWorkers(restaurant);
    }

    private void removeProviders(Restaurant restaurant) {
        restaurant.getAdministrator().getProvidersList()
                .forEach(provider -> provider.removeClient(restaurant));
    }

    private void removeWorkers(Restaurant restaurant) {
        restaurant.getWorkers()
                .forEach(worker -> committer.commitRemoveWorker(restaurant,worker));
    }

    public void closeProvider(Provider provider) {
        Simulation.getProviderList().remove(provider);
        removeRestaurants(provider);
    }

    private void removeRestaurants(Provider provider) {
        provider.getCompanyList().stream()
                .filter(companyClient -> companyClient instanceof Restaurant)
                .map(companyClient -> (Restaurant) companyClient)
                .forEach(restaurant -> committer.commitRemoveProvider(restaurant,provider));
    }
}
