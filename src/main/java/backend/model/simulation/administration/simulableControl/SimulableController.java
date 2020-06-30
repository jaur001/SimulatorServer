package backend.model.simulation.administration.simulableControl;

import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.ComplexCompanyWithStaff;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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
        retiredWorkers = new CopyOnWriteArrayList<>();
        deadClientList = new CopyOnWriteArrayList<>();
        closedCompanyList = new CopyOnWriteArrayList<>();
    }

    public void initIO(SimulationIOController simulationIOController){
        this.simulationIOController = simulationIOController;
    }

    public void manageSimulation() {
        makeChanges();
        simulationIOController.manageSimulablesToAdd();
    }

    public void retire(Worker worker) {
        retiredWorkers.add(worker);
    }

    public void addSimulableForCompany(ComplexCompany company, Simulable simulable) {
        if(!companiesAddingChanges.containsKey(company)) companiesAddingChanges.put(company,new CopyOnWriteArrayList<>());
        companiesAddingChanges.get(company).add(simulable);
    }

    public void removeSimulableForCompany(ComplexCompany company, Simulable simulable) {
        if(!companiesRemovingChanges.containsKey(company)) companiesRemovingChanges.put(company,new CopyOnWriteArrayList<>());
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
        retireWorkers();
        closeCompanies();
        addChangesForCompany();
        removeChangesFromCompany();
        commitChanges();
    }

    private void retireWorkers() {
        retiredWorkers.forEach(worker -> committer.commitRetireUnemployedWorker(worker));
    }

    private void addChangesForCompany() {
        companiesAddingChanges.forEach(this::addSimulables);
    }

    private void addSimulables(ComplexCompany company, List<Simulable> simulables) {
        simulables.forEach(simulable -> addSimulable(company,simulable));
    }

    private void addSimulable(ComplexCompany company, Simulable simulable) {
        if(simulable instanceof Provider) committer.commitAddProvider(company,(Provider)simulable);
        else if(simulable instanceof ServiceCompany) committer.commitAddService(company,(ServiceCompany) simulable);
        else if(simulable instanceof Worker && company instanceof ComplexCompanyWithStaff) committer.commitAddWorker((ComplexCompanyWithStaff)company,(Worker)simulable);
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
        else if(simulable instanceof Worker && company instanceof ComplexCompanyWithStaff) removeWorkerFromCompany((ComplexCompanyWithStaff)company,(Worker)simulable);
    }

    private void removeWorkerFromCompany(ComplexCompanyWithStaff company, Worker worker) {
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
        companiesAddingChanges = new ConcurrentHashMap<>();
        retiredWorkers = new CopyOnWriteArrayList<>();
        companiesRemovingChanges = new ConcurrentHashMap<>();
        deadClientList = new CopyOnWriteArrayList<>();
        closedCompanyList = new CopyOnWriteArrayList<>();
    }
}
