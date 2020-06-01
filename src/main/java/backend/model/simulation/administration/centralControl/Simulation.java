package backend.model.simulation.administration.centralControl;

import backend.implementations.routine.strategy.BestRoutineStrategy;
import backend.implementations.routine.strategy.RoutineStrategy;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.secondaryCompany.SecondaryCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulables.person.worker.jobSearcher.AlwaysAcceptStrategy;
import backend.model.simulables.person.worker.jobSearcher.SelectOfferStrategy;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.view.loaders.database.DatabaseManager;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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

    public static List<Restaurant> getRestaurantList(int page) {
        int from = DatabaseManager.getFrom(page);
        int to = DatabaseManager.getTo(from,getRestaurantSize());
        return getRestaurantList().subList(from, to);
    }

    public static List<Provider> getProviderList(int page) {
        int from = DatabaseManager.getFrom(page);
        int to = DatabaseManager.getTo(from,getProviderSize());
        return getProviderList().subList(from, to);
    }

    public static List<ServiceCompany> getServiceCompanyList(int page) {
        int from = DatabaseManager.getFrom(page);
        int to = DatabaseManager.getTo(from,getServiceCompanySize());
        return getServiceCompanyList().subList(from, to);
    }

    public static List<Client> getClientList(int page) {
        int from = DatabaseManager.getFrom(page);
        int to = DatabaseManager.getTo(from,getClientSize());
        return getClientList().subList(from, to);
    }

    public static List<Worker> getWorkerList(int page) {
        int from = DatabaseManager.getFrom(page);
        int to = DatabaseManager.getTo(from,getWorkerSize());
        return getWorkerList().subList(from, to);
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

    public static List<Client> getClientListCopy() {
        return new CopyOnWriteArrayList<>(getClientList());
    }

    public static List<Worker> getWorkerListCopy() {
        return new CopyOnWriteArrayList<>(getWorkerList());
    }

    private static List<Company> getCompanyList(){
        return SimulationDataController.getSimulationData().getCompanyList();
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
        return SimulationDataController.getSimulationData().getClientList();
    }

    private static List<Worker> getWorkerList() {
        return SimulationDataController.getSimulationData().getWorkerList();
    }

    public static List<Provider> getProviderList(Product product) {
        return getProviderList().stream()
                .filter(provider -> provider.getProduct().equals(product))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<ServiceCompany> getServiceCompanyList(Service service) {
        return getServiceCompanyList().stream()
                .filter(serviceCompany -> serviceCompany.getProduct().equals(service))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Worker> getWorkerList(Job job) {
        return getWorkerList().stream()
                .filter(worker -> worker.getJob().equals(job.toString()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Worker> getUnemployedWorkers(Job job){
        return Simulation.getWorkerList(job).stream()
                .filter(SimulationAdministrator::isNotAlreadyHired)
                .filter(SimulationAdministrator::isNotAlreadyRetired)
                .filter(worker -> !worker.isWorking())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Worker> getEmployedWorkers() {
        return getWorkerList().stream().filter(Worker::isWorking).collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Worker> getUnemployedWorkers() {
        return Simulation.getWorkerList().stream()
                .filter(SimulationAdministrator::isNotAlreadyHired)
                .filter(worker -> !worker.isWorking())
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
