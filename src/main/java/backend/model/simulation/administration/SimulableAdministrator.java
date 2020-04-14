package backend.model.simulation.administration;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.SimulationAdministration;
import backend.model.simulation.administration.SimulationCommitter;
import backend.model.simulation.settings.settingsList.WorkerSettings;
import backend.model.simulation.timeLine.TimeLine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimulableAdministrator {

    private TimeLine timeLine;
    private Map<Restaurant,List<Simulable>> restaurantAddingList;
    private Map<Restaurant,List<Simulable>> restaurantRemovingList;
    private List<Worker> retiredWorkers;
    private List<Client> deadClientList;
    private List<Company> closedCompanyList;

    public SimulableAdministrator() {
        restaurantAddingList = new HashMap<>();
        restaurantRemovingList = new HashMap<>();
        retiredWorkers = new LinkedList<>();
        deadClientList = new LinkedList<>();
        closedCompanyList = new LinkedList<>();
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }

    public void retire(Worker worker) {
        retiredWorkers.add(worker);
    }

    public void addSimulableForCompany(Company company, Simulable simulable) {
        if(company instanceof Restaurant){
            if(!restaurantAddingList.containsKey(company)) restaurantAddingList.put((Restaurant)company,new LinkedList<>());
            restaurantAddingList.get(company).add(simulable);
        }
    }

    public void removeSimulableForCompany(Company company, Simulable simulable) {
        if(company instanceof Restaurant){
            if(!restaurantRemovingList.containsKey(company)) restaurantRemovingList.put((Restaurant)company,new LinkedList<>());
            restaurantRemovingList.get(company).add(simulable);
        }
    }

    public boolean isNotAlreadyHired(Worker worker) {
        return restaurantAddingList.keySet().stream()
                .noneMatch(restaurant-> restaurantAddingList.get(restaurant).contains(worker));
    }

    public boolean isNotAlreadyRetired(Worker worker) {
        return retiredWorkers.stream()
                .noneMatch(retiredWorker -> retiredWorker.equals(worker));
    }

    public void makeChanges() {
        diePeople();
        closeCompanies();
        addSimulablesFromRestaurant();
        removeSimulablesFromRestaurant();
    }

    private void addSimulablesFromRestaurant() {
        retireWorkers();
        restaurantAddingList.forEach(this::addSimulables);
        restaurantAddingList = new HashMap<>();
    }

    private void retireWorkers() {
        retiredWorkers.forEach(Worker::retire);
        retiredWorkers = new LinkedList<>();
    }

    private void addSimulables(Restaurant restaurant, List<Simulable> simulables) {
        simulables.forEach(simulable -> addSimulable(restaurant,simulable));
    }

    private void addSimulable(Restaurant restaurant, Simulable simulable) {
        if(simulable instanceof Provider) SimulationCommitter.commitAddProvider(restaurant,(Provider)simulable);
        else if(simulable instanceof Worker) SimulationCommitter.commitAddWorker(restaurant,(Worker)simulable);
    }

    private void removeSimulablesFromRestaurant() {
        restaurantRemovingList.forEach(this::removeSimulables);
        restaurantRemovingList = new HashMap<>();
    }

    private void removeSimulables(Restaurant restaurant, List<Simulable> simulables) {
        simulables.forEach(simulable -> removeSimulable(restaurant,simulable));
    }

    private void removeSimulable(Restaurant restaurant, Simulable simulable) {
        if(simulable instanceof Provider) SimulationCommitter.commitRemoveProvider(restaurant,(Provider)simulable);
        else if(simulable instanceof Worker) removeWorkerFromRestaurant(restaurant,(Worker)simulable);
    }

    private void removeWorkerFromRestaurant(Restaurant restaurant, Worker worker) {
        if(WorkerSettings.isInRetireAge(worker)) SimulationCommitter.commitRetireWorker(restaurant,worker);
        else SimulationCommitter.commitRemoveWorker(restaurant,worker);
    }

    public void isGoingToDie(Client client){
        deadClientList.add(client);
    }

    private void diePeople(){
        deadClientList.forEach(this::diePerson);
        deadClientList = new LinkedList<>();
    }

    private void diePerson(Client client) {
        SimulationAdministration.diePerson(client);
        timeLine.removeSimulable(client);
    }

    public void isGoingToClose(Company company){
        closedCompanyList.add(company);
    }

    private void closeCompanies(){
        closedCompanyList.forEach(this::closeCompany);
        closedCompanyList = new LinkedList<>();
    }

    private void closeCompany(Company company) {
        SimulationAdministration.closeCompany(company);
        timeLine.removeSimulable(company);
    }
}
