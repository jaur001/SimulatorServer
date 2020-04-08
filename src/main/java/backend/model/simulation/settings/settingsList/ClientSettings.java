package backend.model.simulation.settings.settingsList;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.settings.Adjustable;
import backend.model.simulation.settings.SettingsData;
import backend.model.simulation.settings.data.ClientData;
import backend.utils.MathUtils;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ClientSettings implements Adjustable {

    public static final double PERCENTAGE_FOR_RESTAURANT = 0.148;
    private static final int SALARY_MEAN = 1717;
    private static final double SALARY_SD = 979.28;
    private static final double MIN_SALARY = 500;
    private static final int INVITED_PEOPLE_MIN = 0;
    private static final int INVITED_PEOPLE_MAX = 3;
    private static final int NUM_OF_RESTAURANT_MIN = 1;
    private static final int NUM_OF_RESTAURANT_MAX = 2;
    private static final int CLIENT_SPACE = 85000;

    private static NormalDistribution salaryDistribution;
    private static double minSalary;
    private static Map<Integer,Integer> restaurantGroup = new HashMap<>();

    static {
        getDefaultSettings();
    }

    private static void getDefaultSettings() {
        salaryDistribution = new NormalDistribution(SALARY_MEAN, SALARY_SD);
        minSalary = MIN_SALARY;
        getDefaultSalaryGroups();
    }

    private static void getDefaultSalaryGroups() {
        Integer[] clientSalaries = {1000,2000,3000,4000};
        Integer[] prices = {15,25,40,60};
        IntStream.range(0,clientSalaries.length).boxed()
                .forEach(i -> restaurantGroup.put(clientSalaries[i],prices[i]));
    }

    @Override
    public void init(SettingsData data) {
        ClientData clientData = data.getClientData();
        salaryDistribution = new NormalDistribution(clientData.getSalaryMean(),clientData.getSalarySd());
        minSalary = clientData.getMinSalary();
        restaurantGroup = clientData.getRestaurantGroup();
    }

    @Override
    public void setDefault() {
        getDefaultSettings();
    }

    public static int getSalaryGroup(double salary) {
        List<Integer> salaryOptionList = new ArrayList<>(restaurantGroup.keySet());
        return salaryOptionList.stream()
                .filter(salaryAuxOption -> salary<=salaryAuxOption)
                .findFirst().orElse(salaryOptionList.get(salaryOptionList.size() - 1));
    }

    public static double getSalarySample() {
        double salary = Math.max(salaryDistribution.sample(), minSalary);
        if(Double.isNaN(salary)) return getSalarySample();
        return salary;
    }

    public static double getPeopleInvitedMean() {
        return MathUtils.twoNumberMean(INVITED_PEOPLE_MIN,INVITED_PEOPLE_MAX);
    }

    public static int getPeopleInvitedSample(){
        return MathUtils.random(INVITED_PEOPLE_MIN, INVITED_PEOPLE_MAX+1);
    }

    public static int getNumOfRestaurantSample(){
        return MathUtils.random(NUM_OF_RESTAURANT_MIN, NUM_OF_RESTAURANT_MAX+1);
    }

    public static Restaurant[] getRestaurantOptions(double salary, List<Restaurant> restaurantList){
        int price = getSalaryGroup(salary);
        return restaurantList.stream()
                .filter(restaurant -> restaurant.getMaxPricePlate() <= price)
                .toArray(Restaurant[]::new);
    }

    public static int getLimit(){
        int limit = 0;
        try {
            limit = new SQLiteTableSelector().readCount("Person");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        limit = Math.max(limit - WorkerSettings.WORKER_SPACE, 0);
        return Math.min(limit,CLIENT_SPACE);
    }

    public static int getNextVisitDaySample(double salary, double salaryOption) {
        return MathUtils.random(7,14);
    }

    public static double getMinSalary(){
        return minSalary;
    }
}
