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

    private int plateNumberMean;
    private double plateNumberSD;

    private static final double PERCENTAGE_PROVIDERS = 0.9;
    private int clientCount;
    private int restaurantCount;
    private int providerCount;
    private int serviceCount;
    private int workerCount;

    private Integer[] clientSalaries;
    private Integer[] prices;
    private Map<Integer,Integer> restaurantGroup = new LinkedHashMap<>();
    private static final int SALARY_MEAN = 1717;
    private static final double SALARY_SD = 979.28;
    private static final int MIN_SALARY = 500;

    private static final int INITIAL_SOCIAL_CAPITAL_PROVIDER = 10000;
    private Map<Product, Integer> productCostTable = new LinkedHashMap<>();

    private Map<Job, Integer> workerSalaryTable = new LinkedHashMap<>();
    private static final double INITIAL_SOCIAL_CAPITAL_RESTAURANT = 10000;

    @PostConstruct
    public void init(){
        initDefaultGeneralSettings();
        initDefaultBillSettings();
        initDefaultClientSettings();
        initDefaultProviderSettings();
        initDefaultRestaurantSettings();
    }

    private void initDefaultGeneralSettings() {
        clientCount = 1000;
        restaurantCount = 25;
        providerCount = 500;
        serviceCount = 100;
        workerCount = 1000;
    }

    private void initDefaultBillSettings() {
        plateNumberSD = 0.7;
        plateNumberMean = 2;
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
        return new GeneralData(clientCount,restaurantCount,providerCount,serviceCount,workerCount);
    }

    public BillData getDefaultBillSettings(){
        return new BillData(plateNumberMean, plateNumberSD);
    }

    public ClientData getDefaultClientSettings(){
        return new ClientData(SALARY_MEAN,SALARY_SD,MIN_SALARY,restaurantGroup);
    }

    /*public WorkerData getDefaultWorkerSettings(){

    }*/

    public ProviderData getDefaultProviderSettings(){
        return new ProviderData(INITIAL_SOCIAL_CAPITAL_PROVIDER,productCostTable);
    }

    /*public ServiceData getDefaultServiceSettings(){

    }*/

    public RestaurantData getDefaultRestaurantSettings(){
        return new RestaurantData(INITIAL_SOCIAL_CAPITAL_RESTAURANT,workerSalaryTable);
    }
}
