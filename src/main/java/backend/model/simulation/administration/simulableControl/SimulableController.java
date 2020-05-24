package backend.model.simulation.administration.simulableControl;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompanyWithStaff.ComplexWorkerWithStaff;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimulableController {

    private Map<ComplexCompany,List<Simulable>> companiesAddingChanges;
    private Map<ComplexCompany,List<Simulable>> companiesRemovingChanges;
    private List<Worker> retiredWorkers;
    private List<Client> deadClientList;
    private List<Company> closedCompanyList;
    private SimulationCommitter committer;
    private SimulationIOController simulationIOController;

    public SimulableController(SimulationCommitter committer) {
        this.committer = committer;
        companiesAddingChanges = new ConcurrentHashMap<>();
        companiesRemovingChanges = new ConcurrentHashMap<>();
        retiredWorkers = new LinkedList<>();
        deadClientList = new LinkedList<>();
        closedCompanyList = new LinkedList<>();
    }

    public void initIO(SimulationIOController simulationIOController){
        this.simulationIOController = simulationIOController;
    }

    public void manageSimulation() {
        simulationIOController.manageSimulation();
    }

    public void retire(Worker worker) {
        retiredWorkers.add(worker);
    }

    public void addSimulableForCompany(ComplexCompany company, Simulable simulable) {
        if(!companiesAddingChanges.containsKey(company)) companiesAddingChanges.put(company,new LinkedList<>());
        companiesAddingChanges.get(company).add(simulable);
    }

    public void removeSimulableForCompany(ComplexCompany company, Simulable simulable) {
        if(!companiesRemovingChanges.containsKey(company)) companiesRemovingChanges.put(company,new LinkedList<>());
        companiesRemovingChanges.get(company).add(simulable);
    }

    public boolean isNotAlreadyHired(Worker worker) {
        return companiesAddingChanges.keySet().stream()
                .noneMatch(company-> companiesAddingChanges.get(company).contains(worker));
    }

    public boolean isNotAlreadyRetired(Worker worker) {
        return retiredWorkers.stream()
                .noneMatch(retiredWorker -> retiredWorker.equals(worker));
    }

    public void makeChanges() {
        diePeople();
        closeCompanies();
        addChangesForCompany();
        removeChangesFromCompany();
        commitChanges();
    }

    private void addChangesForCompany() {
        retireWorkers();
        companiesAddingChanges.forEach(this::addSimulables);
    }

    private void retireWorkers() {
        retiredWorkers.forEach(Worker::retire);
    }

    private void addSimulables(ComplexCompany company, List<Simulable> simulables) {
        simulables.forEach(simulable -> addSimulable(company,simulable));
    }

    private void addSimulable(ComplexCompany company, Simulable simulable) {
        if(simulable instanceof Provider) committer.commitAddProvider(company,(Provider)simulable);
        else if(simulable instanceof ServiceCompany) committer.commitAddService(company,(ServiceCompany) simulable);
        else if(simulable instanceof Worker && company instanceof ComplexWorkerWithStaff) committer.commitAddWorker((ComplexWorkerWithStaff)company,(Worker)simulable);
    }

    private void removeChangesFromCompany() {
        companiesRemovingChanges.forEach(this::removeSimulables);
    }

    private void removeSimulables(ComplexCompany company, List<Simulable> simulables) {
        simulables.forEach(simulable -> removeSimulable(company,simulable));
    }

    private void removeSimulable(ComplexCompany company, Simulable simulable) {
        if(simulable instanceof Provider) committer.commitRemoveProvider(company,(Provider)simulable);
        else if(simulable instanceof ServiceCompany) committer.commitRemoveService(company,(ServiceCompany) simulable);
        else if(simulable instanceof Worker && company instanceof ComplexWorkerWithStaff) removeWorkerFromCompany((ComplexWorkerWithStaff)company,(Worker)simulable);
    }

    private void removeWorkerFromCompany(ComplexWorkerWithStaff company, Worker worker) {
        if(WorkerSettings.isInRetireAge(worker)) committer.commitRetireWorker(company,worker);
        else committer.commitRemoveWorker(company,worker);
    }

    public void isGoingToDie(Client client){
        deadClientList.add(client);
    }

    private void diePeople(){
        deadClientList.forEach(client -> simulationIOController.diePerson(client));
    }

    public void isGoingToClose(Company company){
        closedCompanyList.add(company);
    }

    private void closeCompanies(){
        closedCompanyList.forEach(this::closeCompany);
    }

    public void closeCompany(Company company) {
        simulationIOController.closeCompany(company);
    }

    private void commitChanges() {
        companiesAddingChanges = new HashMap<>();
        retiredWorkers = new LinkedList<>();
        companiesRemovingChanges = new HashMap<>();
        deadClientList = new LinkedList<>();
        closedCompanyList = new LinkedList<>();
    }
}
