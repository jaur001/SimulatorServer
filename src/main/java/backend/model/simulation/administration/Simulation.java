package backend.model.simulation.administration;

import backend.implementations.SQLite.controllers.SQLiteRowDeleter;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.implementations.routine.strategy.BestRoutineStrategy;
import backend.implementations.routine.strategy.RoutineStrategy;
import backend.model.bill.generator.XMLBill;
import backend.model.event.EventController;
import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
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
import backend.model.simulation.settings.settingsList.GeneralSettings;
import backend.utils.DatabaseUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Simulation {

    private static List<Company> companyList = new CopyOnWriteArrayList<>();
    private static List<Client> clientList = new CopyOnWriteArrayList<>();
    private static List<Worker> workerList = new CopyOnWriteArrayList<>();
    private static List<XMLBill> billList = new LinkedList<>();

    public static final SelectOfferStrategy SEARCHER_STRATEGY = new AlwaysAcceptStrategy();
    public static final RoutineStrategy ROUTINE_STRATEGY = new BestRoutineStrategy();

    public static int getProviderSize() {
        return getProviderList().size();
    }

    public static int getServiceCompanySize() {
        return getServiceCompanyList().size();
    }

    public static int getRestaurantSize() {
        return getRestaurantList().size();
    }

    public static int getClientSize() {
        return clientList.size();
    }

    public static int getWorkerSize() {
        return workerList.size();
    }

    private static int getFrom(int page) {
        return DatabaseUtils.getPageLength()*(page-1);
    }

    private static int getTo(int from, int to) {
        return Math.min(from + DatabaseUtils.getPageLength(), to);
    }

    public static List<Restaurant> getRestaurantList(int page) {
        int from = getFrom(page);
        int to = getTo(from,getRestaurantSize());
        return getRestaurantList().subList(from, to);
    }

    public static List<Provider> getProviderList(int page) {
        int from = getFrom(page);
        int to = getTo(from,getProviderSize());
        return getProviderList().subList(from, to);
    }

    public static List<Client> getClientList(int page) {
        int from = getFrom(page);
        int to = getTo(from,clientList.size());
        return clientList.subList(from, to);
    }

    public static List<Worker> getWorkerList(int page) {
        int from = getFrom(page);
        int to = getTo(from,workerList.size());
        return workerList.subList(from, to);
    }

    public static List<XMLBill> getBillList(int page) {
        int from = getFrom(page);
        int to = getTo(from,billList.size());
        if(billList.size()>(page-1)*DatabaseUtils.getPageLength())return billList.subList(from, to);
        else return getFromDatabase(page);
    }

    private static List<XMLBill> getFromDatabase(int page) {
        try {
            return new BillBuilder().buildList(new SQLiteTableSelector().read("Bill",page));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
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
        return new CopyOnWriteArrayList<>(clientList);
    }

    public static List<Worker> getWorkerListCopy() {
        return new CopyOnWriteArrayList<>(workerList);
    }

    static List<Company> getCompanyList(){
        return companyList;
    }

    static List<Restaurant> getRestaurantList() {
        return companyList.stream()
                .filter(company -> company instanceof Restaurant)
                .map(company -> (Restaurant) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    static List<Provider> getProviderList() {
        return companyList.stream()
                .filter(company -> company instanceof Provider)
                .map(company -> (Provider) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    static List<ServiceCompany> getServiceCompanyList() {
        return companyList.stream()
                .filter(company -> company instanceof ServiceCompany)
                .map(company -> (ServiceCompany) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    static List<Client> getClientList() {
        return clientList;
    }

    static List<Worker> getWorkerList() {
        return workerList;
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
        return workerList.stream()
                .filter(worker -> worker.getJob().equals(job.toString()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static void addBill(XMLBill bill){
        if(billList.size()<DatabaseUtils.getListLimit())billList.add(bill);
    }


    public static List<Simulable> init(){
        initElements();
        return Initializer.init();
    }

    private static void initElements() {
        try {
            companyList.addAll(Initializer.getServiceCompany(GeneralSettings.getServiceCount()));
            companyList.addAll(Initializer.getProviders(GeneralSettings.getProviderCount()));
            companyList.addAll(Initializer.getRestaurants(GeneralSettings.getRestaurantCount()));
            workerList = Initializer.getWorkers(GeneralSettings.getWorkerCount());
            clientList = Initializer.getClients(GeneralSettings.getClientCount());
            clientList.addAll(workerList);
        } catch (SQLException | ClassNotFoundException e) {
            Simulator.waitForOtherElements();
            initElements();
        }
    }

    public static void reset(){
        companyList = new CopyOnWriteArrayList<>();
        clientList = new CopyOnWriteArrayList<>();
        workerList = new CopyOnWriteArrayList<>();
        resetBills();
        resetEvents();
    }

    private static void resetBills(){
        billList = new LinkedList<>();
        try {
            if(new SQLiteTableSelector().readCount("Bill")!=0)
                new SQLiteRowDeleter().deleteAll("Bill");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database is locked, could not delete Bills of last Simulation");
        }
    }

    private static void resetEvents() {
        EventController.resetEvents();
    }

    public static List<Worker> getUnemployedWorkers(Job job){
        return Simulation.getWorkerList(job).stream()
                .filter(Simulator::isNotAlreadyHired)
                .filter(Simulator::isNotAlreadyRetired)
                .filter(worker -> !worker.isWorking())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Worker> getEmployedWorkers() {
        return workerList.stream().filter(Worker::isWorking).collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Worker> getUnemployedWorkers() {
        return Simulation.getWorkerList().stream()
                .filter(Simulator::isNotAlreadyHired)
                .filter(worker -> !worker.isWorking())
                .collect(Collectors.toCollection(LinkedList::new));
    }
}