package backend.model.simulation.settings.settingsList;

import backend.implementations.routine.GenericRoutineFactory;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.settings.Adjustable;
import backend.model.simulation.settings.SettingsData;
import backend.model.simulation.settings.data.ClientData;
import backend.utils.MathUtils;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.*;
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
    public static final int DEATH_AGE = 75;

    private static NormalDistribution salaryDistribution;
    private static double minSalary;
    private static Map<Integer,Integer> restaurantGroup = new HashMap<>();
    private static Integer[] clientSalaries;
    private static Integer[] prices;

    static {
        getDefaultSettings();
    }

    private static void getDefaultSettings() {
        salaryDistribution = new NormalDistribution(SALARY_MEAN, SALARY_SD);
        minSalary = MIN_SALARY;
        getDefaultSalaryGroups();
    }

    private static void getDefaultSalaryGroups() {
        clientSalaries = new Integer[]{1000,2000,3000,4000};
        prices = new Integer[]{15,25,40,60};
        IntStream.range(0, clientSalaries.length).boxed()
                .forEach(i -> restaurantGroup.put(clientSalaries[i], prices[i]));
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

    public static Restaurant[] getRestaurantOptions(int salaryOption){
        int price = getPrices(salaryOption);
        return Simulation.getRestaurantListCopy().stream()
                .filter(Objects::nonNull)
                .filter(restaurant -> restaurant.getMaxPricePlate() <= price)
                .toArray(Restaurant[]::new);
    }

    private static int getPrices(int salaryOption) {
        return restaurantGroup.get(salaryOption);
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

    public static int getNextVisitDaySample(double salary, double salaryOption) {
        return MathUtils.random(3,30);
    }

    public static double getMinSalary(){
        return minSalary;
    }

    private static boolean isInDeathAge(double age){
        return age >= DEATH_AGE;
    }

    public static boolean isGoingToDie(double age){
        if(isInDeathAge(age)) return MathUtils.random(0,5000)< 2;
        return false;
    }

    public static List<Routine> getRoutineList(double salary) {
        return new GenericRoutineFactory(Simulation.ROUTINE_STRATEGY,salary).createRoutineList();
    }

    public static boolean newClient() {
        return MathUtils.calculateProbability(100-(int)getClientRestaurantPercentage());
    }

    private static double getClientRestaurantPercentage(){
        return ((double)Simulation.getClientSize()/(double)(1+(Simulation.getRestaurantSize()*RestaurantSettings.getClientLimitMean())))*100.0;
    }
}
