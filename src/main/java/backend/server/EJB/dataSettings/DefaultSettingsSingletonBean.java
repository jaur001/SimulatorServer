package backend.server.EJB.dataSettings;

import backend.implementations.SQLite.SQLiteTableAdministrator;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.person.worker.Job;
import backend.server.EJB.dataSettings.data.*;
import backend.view.loaders.database.TableAdministrator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton(name = "DefaultSettingsSingletonEJB")
public class DefaultSettingsSingletonBean {

    private TableAdministrator administrator;
    private GeneralData defaultGeneralData;
    private ClientData defaultClientData;
    private RestaurantData defaultRestaurantData;
    private ProviderData defaultProviderData;
    private ServiceData defaultServiceData;
    private WorkerData defaultWorkerData;

    public DefaultSettingsSingletonBean() {
        administrator = new SQLiteTableAdministrator();
        init();
    }

    public void init(){
        try {
            initGeneralData();
            initClientData();
            initRestaurantData();
            initProviderData();
            initServiceData();
            initWorkerData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initGeneralData() throws SQLException, ClassNotFoundException {
        defaultGeneralData = (GeneralData) administrator.read(0,1,GeneralData.class).get(0);
    }

    private void initClientData() throws SQLException, ClassNotFoundException {
        defaultClientData = (ClientData) administrator.read(0,1,ClientData.class).get(0);
        defaultClientData.setRestaurantGroup(initRestaurantGroups());
    }

    public void initRestaurantData() throws SQLException, ClassNotFoundException {
        defaultRestaurantData = (RestaurantData) administrator.read(0,1,RestaurantData.class).get(0);
        defaultRestaurantData.setWorkerSalaryTable(initWorkerSalaryTable());
    }

    public void initProviderData() throws SQLException, ClassNotFoundException {
        defaultProviderData = (ProviderData) administrator.read(0,1,ProviderData.class).get(0);
        defaultProviderData.setProductSalePriceTable(initProductSaleTable());
    }

    public void initServiceData() throws SQLException, ClassNotFoundException {
        defaultServiceData = (ServiceData) administrator.read(0,1,ServiceData.class).get(0);
        defaultServiceData.setServicePriceTable(initServicePriceTable());
    }

    public void initWorkerData() throws SQLException, ClassNotFoundException {
        defaultWorkerData = (WorkerData) administrator.read(0,1,WorkerData.class).get(0);
    }

    private Map<Integer,Integer> initRestaurantGroups() {
        Integer[] clientSalaries = new Integer[]{1000,2000,3000,4000};
        Integer[] prices = new Integer[]{15,25,40,60};
        Map<Integer,Integer> restaurantGroup = new LinkedHashMap<>();
        IntStream.range(0, clientSalaries.length).boxed()
                .forEach(i -> restaurantGroup.put(clientSalaries[i], prices[i]));
        return restaurantGroup;
    }

    private Map<Job, Integer> initWorkerSalaryTable(){
        Integer[] salaries = {800,1000,1500,3000,3000};
        Map<Job, Integer> workerSalaryTable = new LinkedHashMap<>();
        IntStream.range(0,Job.values().length).boxed()
                .forEach(i -> workerSalaryTable.put(Job.values()[i],salaries[i]));
        return workerSalaryTable;
    }
    private Map<Product, Double> initProductSaleTable() {
        Double[] cost = {150.0,160.0,160.0,80.0,80.0,100.0,90.0};
        Map<Product, Double> productSalePriceTable = new LinkedHashMap<>();
        IntStream.range(0,Product.values().length).boxed()
                .forEach(i -> productSalePriceTable.put(Product.values()[i],cost[i]));
        return productSalePriceTable;
    }

    private Map<Service, Double> initServicePriceTable() {
        Double[] prices = new Double[]{100.0,100.0};
        Map<Service, Double> servicePriceTable = new LinkedHashMap<>();
        IntStream.range(0,Service.values().length).boxed()
                .forEach(i -> servicePriceTable.put(Service.values()[i],prices[i]));
        return servicePriceTable;
    }

    public GeneralData getDefaultGeneralData(){
        return defaultGeneralData;
    }

    public ClientData getDefaultClientData(){
        return defaultClientData;
    }

    public RestaurantData getDefaultRestaurantData(){
        return defaultRestaurantData;
    }

    public ProviderData getDefaultProviderData(){
        return defaultProviderData;
    }

    public ServiceData getDefaultServiceData() {
        return defaultServiceData;
    }

    public WorkerData getDefaultWorkerData(){
        return defaultWorkerData;
    }
}
