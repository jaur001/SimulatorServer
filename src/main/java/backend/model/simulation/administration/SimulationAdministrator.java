package backend.model.simulation.administration;

import backend.model.event.EventController;
import backend.model.event.EventGenerator;
import backend.model.event.events.client.NewClientEvent;
import backend.model.event.events.company.NewCompanyEvent;
import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.*;

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
        checkThereIsNewService();
    }

    private void checkThereIsNewRestaurant() {
        if(!RestaurantSettings.newRestaurant()) return;
        Simulable simulable = addRestaurant();
        if(simulable != null)addEvent(new NewCompanyEvent((ComplexCompany)simulable));
        addSimulable(simulable);
    }

    private void checkThereIsNewProvider() {
        if(!ProviderSettings.newProvider()) return;
        Simulable simulable = addProvider();
        if(simulable != null)addEvent(new NewCompanyEvent((ComplexCompany)simulable));
        addSimulable(simulable);
    }

    private void checkThereIsNewService() {
        if(!ServiceSettings.newService()) return;
        Simulable simulable = addService();
        if(simulable != null)addEvent(new NewCompanyEvent((ComplexCompany)simulable));
        addSimulable(simulable);
    }

    public void addSimulable(Simulable simulable){
        if(simulable!=null){
            simulableList.add(simulable);
        }
    }

    public void removeSimulable(Simulable simulable) {
        simulableList.remove(simulable);
    }

    private Simulable addWorker() {
        Worker worker = Initializer.getWorker();
        if(worker == null) return null;
        Simulation.getWorkerList().add(worker);
        Simulation.getClientList().add(worker);
        return worker;
    }

    private Simulable addClient() {
        Client client = Initializer.getClient();
        if(client == null) return null;
        Simulation.getClientList().add(client);
        return client;
    }

    private Simulable addRestaurant() {
        Restaurant restaurant = Initializer.getRestaurant();
        if(restaurant == null) return null;
        Simulator.makeChanges();
        if(restaurant.getNumberOfWorkers()==0) return null;
        Simulation.getCompanyList().add(restaurant);
        return restaurant;
    }

    private Simulable addProvider() {
        Provider provider = Initializer.getProvider();
        if(provider == null) return null;
        Simulation.getCompanyList().add(provider);
        return provider;
    }

    private Simulable addService() {
        ServiceCompany serviceCompany = Initializer.getService();
        if(serviceCompany == null) return null;
        Simulation.getCompanyList().add(serviceCompany);
        return serviceCompany;
    }

    public void diePerson(Client client) {
        committer.commitDiePerson(client);
        removeSimulable(client);
    }

    public void closeCompany(Company company) {
        committer.commitCloseCompany(company);
        removeSimulable(company);
    }
}
