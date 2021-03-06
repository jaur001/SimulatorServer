package backend.model.simulation.administration.centralControl;

import backend.implementations.routine.strategy.BestRoutineStrategy;
import backend.implementations.routine.strategy.RoutineStrategy;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.secondaryCompany.SecondaryCompany;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Product;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulables.person.worker.jobSearcher.AlwaysAcceptStrategy;
import backend.model.simulables.person.worker.jobSearcher.SelectOfferStrategy;
import backend.model.simulation.administration.data.SimulationDataAdministrator;
import backend.view.loaders.database.DatabaseManager;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Simulation {

    public static final SelectOfferStrategy SEARCHER_STRATEGY = new AlwaysAcceptStrategy();
    public static final RoutineStrategy ROUTINE_STRATEGY = new BestRoutineStrategy();

    public static int getCompanySize() {
        return getCompanyList().size();
    }

    public static int getProviderSize() {
        return getProviderList().size();
    }

    public static int getServiceCompanySize() {
        return getServiceCompanyList().size();
    }

    public static int getSecondaryCompanySize(){
        return (int)getCompanyList().stream()
                .filter(company -> company instanceof SecondaryCompany)
                .count();
    }

    public static int getRestaurantSize() {
        return getRestaurantList().size();
    }

    public static int getClientSize() {
        return getClientList().size();
    }

    public static int getWorkerSize() {
        return getWorkerList().size();
    }

    public static List<Company> getCompanyListCopy() {
        return new CopyOnWriteArrayList<>(getCompanyList());
    }

    public static List<Restaurant> getRestaurantListCopy() {
        return new CopyOnWriteArrayList<>(getRestaurantList());
    }

    public static List<Provider> getProviderListCopy() {
        return new CopyOnWriteArrayList<>(getProviderList());
    }

    public static List<ServiceCompany> getServiceCompanyListCopy() {
        return new CopyOnWriteArrayList<>(getServiceCompanyList());
    }

    public static List<Client> getClientListCopy() {
        return new CopyOnWriteArrayList<>(getClientList());
    }

    public static List<Worker> getWorkerListCopy() {
        return new CopyOnWriteArrayList<>(getWorkerList());
    }

    private static List<Company> getCompanyList(){
        return SimulationDataAdministrator.getSimulationData().getCompanyList();
    }

    private static List<Restaurant> getRestaurantList() {
        return getCompanyList().stream()
                .filter(company -> company instanceof Restaurant)
                .map(company -> (Restaurant) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    private static List<Provider> getProviderList() {
        return getCompanyList().stream()
                .filter(company -> company instanceof Provider)
                .map(company -> (Provider) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    private static List<ServiceCompany> getServiceCompanyList() {
        return getCompanyList().stream()
                .filter(company -> company instanceof ServiceCompany)
                .map(company -> (ServiceCompany) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    private static List<Client> getClientList() {
        return SimulationDataAdministrator.getSimulationData().getClientList();
    }

    private static List<Worker> getWorkerList() {
        return getClientList().stream()
                .filter(client -> client instanceof Worker)
                .map(client -> (Worker) client)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    public static List<Provider> getProviderList(Product product) {
        return getProviderList().stream()
                .filter(provider -> provider.getProduct().equals(product))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    public static List<ServiceCompany> getServiceCompanyList(Service service) {
        return getServiceCompanyList().stream()
                .filter(serviceCompany -> serviceCompany.getProduct().equals(service))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    public static List<Worker> getWorkerList(Job job) {
        return getWorkerList().stream()
                .filter(worker -> worker.getJob().equals(job.toString()))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    public static List<Worker> getUnemployedWorkers(Job job){
        return getUnemployedWorkers(Simulation.getWorkerList(job));
    }

    public static List<Worker> getEmployedWorkers() {
        return getWorkerList().stream().filter(Worker::isWorking).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    public static List<Worker> getUnemployedWorkers() {
        return getUnemployedWorkers(Simulation.getWorkerList());
    }

    private static List<Worker> getUnemployedWorkers(List<Worker> workerList){
        return workerList.stream()
                .filter(SimulationAdministrator::isNotAlreadyHired)
                .filter(SimulationAdministrator::isNotAlreadyRetired)
                .filter(worker -> !worker.isWorking())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }
}
