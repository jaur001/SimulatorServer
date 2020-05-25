package backend.server.EJB.dataSettings;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.person.worker.Job;
import backend.server.EJB.dataSettings.data.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton(name = "DefaultSettingsSingletonEJB")
public class DefaultSettingsSingletonBean {

    //General
    private static final int WORKER_COUNT = 1000;
    private static final int CLIENT_COUNT = 1000;
    private static final int RESTAURANT_COUNT = 25;
    private static final int PROVIDER_COUNT = 500;
    private static final int SERVICE_COUNT = 100;

    //Client
    private Integer[] clientSalaries;
    private Integer[] prices;
    private static final Map<Integer,Integer> restaurantGroup = new LinkedHashMap<>();
    private static final DistributionData SALARY_CLIENT = new DistributionData(1717,979.28);
    private static final int MIN_SALARY_CLIENT = 500;
    private static final MinMaxData INVITED_PEOPLE = new MinMaxData(0,3);
    private static final MinMaxData NUM_OF_RESTAURANT = new MinMaxData(1,5);
    private static final DistributionData PLATE_NUMBER = new DistributionData(2,0.7);

    //Restaurant
    private Map<Job, Integer> workerSalaryTable = new LinkedHashMap<>();
    private static final double INITIAL_SOCIAL_CAPITAL_RESTAURANT = 10000;
    public static final MinMaxData LENGTH_CONTRACT = new MinMaxData(90,360);
    public static final double RESTAURANT_PRICE_CHANGE = 0.02;
    private static final double CAPACITY = 1.0;
    public static final double RESTAURANT_CLOSE_LIMIT = -5000.0;

    //Provider
    private static final int INITIAL_SOCIAL_CAPITAL_PROVIDER = 10000;
    private Map<Product, Double> productSalePriceTable = new LinkedHashMap<>();
    public static final double PROVIDER_PRICE_CHANGE = 0.01;
    private static final double PROVIDER_CLOSE_LIMIT = -5000.0;
    
    //Service
    private static final int INITIAL_SOCIAL_CAPITAL_SERVICE = 10000;
    private Map<Service, Double> servicePriceTable = new LinkedHashMap<>();
    public static final double SERVICE_PRICE_CHANGE = 0.01;
    private static final double SERVICE_CLOSE_LIMIT = -5000.0;

    //Worker
    private static final double MIN_SALARY_WORKER = 500.0;
    private static final double SALARY_CHANGE = 0.05;
    private static final double SALARY_DESIRED_CHANGE = 0.001;
    private static final int RETIRE_AGE = 65;
    private static final double PERCENTAGE_RETIREMENT = 0.60;

    public DefaultSettingsSingletonBean() {
        init();
    }

    @PostConstruct
    public void init(){
        initDefaultClientData();
        initDefaultRestaurantData();
        initDefaultProviderData();
        initDefaultServiceData();
    }

    private void initDefaultClientData() {
        clientSalaries = new Integer[]{1000,2000,3000,4000};
        prices = new Integer[]{15,25,40,60};
        IntStream.range(0, clientSalaries.length).boxed()
                .forEach(i -> restaurantGroup.put(clientSalaries[i], prices[i]));
    }

    private void initDefaultRestaurantData(){
        Integer[] salaries = {800,1000,1500,3000,3000};
        IntStream.range(0,Job.values().length).boxed()
                .forEach(i -> workerSalaryTable.put(Job.values()[i],salaries[i]));
    }
    private void initDefaultProviderData() {
        Double[] cost = {150.0,160.0,160.0,80.0,80.0,100.0,90.0};
        IntStream.range(0,Product.values().length).boxed()
                .forEach(i -> productSalePriceTable.put(Product.values()[i],cost[i]));
    }

    private void initDefaultServiceData() {
        Double[] prices = new Double[]{100.0,100.0};
        IntStream.range(0,Service.values().length).boxed()
                .forEach(i -> servicePriceTable.put(Service.values()[i],prices[i]));
    }

    public GeneralData getDefaultGeneralData(){
        return new GeneralData(CLIENT_COUNT, RESTAURANT_COUNT, PROVIDER_COUNT, SERVICE_COUNT, WORKER_COUNT);
    }

    public ClientData getDefaultClientData(){
        return new ClientData(SALARY_CLIENT,MIN_SALARY_CLIENT,restaurantGroup,INVITED_PEOPLE,NUM_OF_RESTAURANT,PLATE_NUMBER);
    }

    public RestaurantData getDefaultRestaurantData(){
        return new RestaurantData(INITIAL_SOCIAL_CAPITAL_RESTAURANT,workerSalaryTable,LENGTH_CONTRACT, RESTAURANT_PRICE_CHANGE, CAPACITY, RESTAURANT_CLOSE_LIMIT);
    }

    public ProviderData getDefaultProviderData(){
        return new ProviderData(INITIAL_SOCIAL_CAPITAL_PROVIDER, productSalePriceTable, PROVIDER_PRICE_CHANGE, PROVIDER_CLOSE_LIMIT);
    }

    public ServiceData getDefaultServiceData() {
        return new ServiceData(INITIAL_SOCIAL_CAPITAL_SERVICE, servicePriceTable, SERVICE_PRICE_CHANGE, SERVICE_CLOSE_LIMIT);
    }

    public WorkerData getDefaultWorkerData(){
        return new WorkerData(MIN_SALARY_WORKER,SALARY_CHANGE,SALARY_DESIRED_CHANGE,RETIRE_AGE,PERCENTAGE_RETIREMENT);
    }
}
