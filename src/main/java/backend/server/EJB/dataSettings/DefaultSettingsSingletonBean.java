package backend.server.EJB.dataSettings;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
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

    //Bills
    private static final DistributionData PLATE_NUMBER = new DistributionData(2,0.7);

    //Client
    private Integer[] clientSalaries;
    private Integer[] prices;
    private static final Map<Integer,Integer> restaurantGroup = new LinkedHashMap<>();
    private static final int SALARY_MEAN = 1717;
    private static final double SALARY_SD = 979.28;
    private static final int MIN_SALARY = 500;
    private static final MinMaxData INVITED_PEOPLE = new MinMaxData(0,3);
    private static final MinMaxData NUM_OF_RESTAURANT = new MinMaxData(1,5);

    //Restaurant
    private Map<Job, Integer> workerSalaryTable = new LinkedHashMap<>();
    private static final double INITIAL_SOCIAL_CAPITAL_RESTAURANT = 10000;
    public static final MinMaxData LENGTH_CONTRACT = new MinMaxData(90,360);
    public static final double PRICE_CHANGE = 0.02;
    private static final double CAPACITY = 1.0;
    public static final int CLOSE_LIMIT = -5000;

    //Provider
    private static final int INITIAL_SOCIAL_CAPITAL_PROVIDER = 10000;
    private Map<Product, Integer> productCostTable = new LinkedHashMap<>();


    @PostConstruct
    public void init(){
        initDefaultClientSettings();
        initDefaultProviderSettings();
        initDefaultRestaurantSettings();
    }

    private void initDefaultClientSettings() {
        clientSalaries = new Integer[]{1000,2000,3000,4000};
        prices = new Integer[]{15,25,40,60};
        IntStream.range(0, clientSalaries.length).boxed()
                .forEach(i -> restaurantGroup.put(clientSalaries[i], prices[i]));
    }
    private void initDefaultProviderSettings() {
        Integer[] cost = {150,160,160,80,80,100,90};
        IntStream.range(0,Product.values().length).boxed()
                .forEach(i -> productCostTable.put(Product.values()[i],cost[i]));
    }

    private void initDefaultRestaurantSettings(){
        Integer[] salaries = {800,1000,1500,3000,3000};
        IntStream.range(0,Job.values().length).boxed()
                .forEach(i -> workerSalaryTable.put(Job.values()[i],salaries[i]));
    }

    public GeneralData getDefaultGeneralSettings(){
        return new GeneralData(CLIENT_COUNT, RESTAURANT_COUNT, PROVIDER_COUNT, SERVICE_COUNT, WORKER_COUNT);
    }

    public BillData getDefaultBillSettings(){
        return new BillData(PLATE_NUMBER);
    }

    public ClientData getDefaultClientSettings(){
        return new ClientData(SALARY_MEAN,SALARY_SD,MIN_SALARY,restaurantGroup,INVITED_PEOPLE,NUM_OF_RESTAURANT);
    }

    public RestaurantData getDefaultRestaurantSettings(){
        return new RestaurantData(INITIAL_SOCIAL_CAPITAL_RESTAURANT,workerSalaryTable,LENGTH_CONTRACT,PRICE_CHANGE, CAPACITY,CLOSE_LIMIT);
    }

    public ProviderData getDefaultProviderSettings(){
        return new ProviderData(INITIAL_SOCIAL_CAPITAL_PROVIDER,productCostTable);
    }

    /*public WorkerData getDefaultWorkerSettings(){

    }*/

    /*public ServiceData getDefaultServiceSettings(){

    }*/
}
