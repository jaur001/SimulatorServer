package backend.model.simulation.administration;

import backend.implementations.SQLite.controllers.SQLiteRowDeleter;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.implementations.routine.strategy.BestRoutineStrategy;
import backend.implementations.routine.strategy.RoutineStrategy;
import backend.model.bill.generator.XMLBill;
import backend.model.event.EventController;
import backend.model.simulables.Simulable;
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
import backend.model.simulation.settings.settingsList.GeneralSettings;
import backend.server.EJB.BillDataSingletonBean;
import backend.utils.DatabaseUtils;
import backend.utils.MathUtils;
import backend.view.loaders.database.builder.builders.BillBuilder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Simulation {

    private static BillDataSingletonBean billData;

    public static final SelectOfferStrategy SEARCHER_STRATEGY = new AlwaysAcceptStrategy();
    public static final RoutineStrategy ROUTINE_STRATEGY = new BestRoutineStrategy();

    public static void followSimulable(Simulable simulable){
        if(!SimulationDataController.getSessionData().getFollowedSimulables().contains(simulable))SimulationDataController.getSessionData().getFollowedSimulables().add(simulable);
    }

    public static void unfollowSimulable(Simulable simulable){
        SimulationDataController.getSessionData().getFollowedSimulables().remove(simulable);
    }

    public static List<Simulable> getFollowedSimulables(){
        return SimulationDataController.getSessionData().getFollowedSimulables();
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
        int to = getTo(from,getClientSize());
        return getClientList().subList(from, to);
    }

    public static List<Worker> getWorkerList(int page) {
        int from = getFrom(page);
        int to = getTo(from,getWorkerSize());
        return getWorkerList().subList(from, to);
    }

    public static List<XMLBill> getBillList(int page) {
        int from = getFrom(page);
        int to = getTo(from,billData.getSize());
        if(billData.getSize()>(page-1)*DatabaseUtils.getPageLength())return billData.getBillList(from, to);
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
        return new CopyOnWriteArrayList<>(getClientList());
    }

    public static List<Worker> getWorkerListCopy() {
        return new CopyOnWriteArrayList<>(getWorkerList());
    }

    static List<Company> getCompanyList(){
        return SimulationDataController.getSessionData().getCompanyList();
    }

    static List<Restaurant> getRestaurantList() {
        return getCompanyList().stream()
                .filter(company -> company instanceof Restaurant)
                .map(company -> (Restaurant) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    static List<Provider> getProviderList() {
        return getCompanyList().stream()
                .filter(company -> company instanceof Provider)
                .map(company -> (Provider) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    static List<ServiceCompany> getServiceCompanyList() {
        return getCompanyList().stream()
                .filter(company -> company instanceof ServiceCompany)
                .map(company -> (ServiceCompany) company)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    static List<Client> getClientList() {
        return SimulationDataController.getSessionData().getClientList();
    }

    static List<Worker> getWorkerList() {
        return SimulationDataController.getSessionData().getWorkerList();
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

    public static void addBill(XMLBill bill){
        if(billData.getSize()<DatabaseUtils.getListLimit())billData.addBill(bill);
    }


    public static List<Simulable> init(){
        initBillData();
        initSimulables();
        followRandomOptions();
        return Initializer.init();
    }

    private static void initBillData() {
        try {
            billData = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/BillDataSingletonEJB");
        } catch (NamingException e) {
            billData = new BillDataSingletonBean();
        }
    }


    private static void initSimulables(){
        try {
            int serviceCount = GeneralSettings.getServiceCount();
            SimulationDataController.getSessionData().getCompanyList().addAll(Initializer.getServiceCompanies(serviceCount));
            int providerCount = GeneralSettings.getProviderCount();
            SimulationDataController.getSessionData().getCompanyList().addAll(Initializer.getProviders(providerCount));
            int restaurantCount = GeneralSettings.getRestaurantCount();
            SimulationDataController.getSessionData().getCompanyList().addAll(Initializer.getRestaurants(restaurantCount));
            int clientCount = GeneralSettings.getClientCount();
            SimulationDataController.getSessionData().getClientList().addAll(Initializer.getClients(clientCount));
            int workerCount = GeneralSettings.getWorkerCount();
            SimulationDataController.getSessionData().getWorkerList().addAll(Initializer.getWorkers(workerCount));
            SimulationDataController.getSessionData().getClientList().addAll(SimulationDataController.getSessionData().getWorkerList());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void followRandomOptions() {
        followSimulable(getCompanyList().get(MathUtils.random(0,getCompanyList().size())));
        followSimulable(getClientList().get(MathUtils.random(0,getClientSize())));
    }

    public static void reset(){
        SimulationDataController.getSessionData().reset();
        resetBills();
        resetEvents();
    }

    private static void resetBills(){
        billData.reset();
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
