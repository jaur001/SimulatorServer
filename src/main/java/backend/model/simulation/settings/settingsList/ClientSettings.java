package backend.model.simulation.settings.settingsList;

import backend.implementations.routine.GenericRoutineFactory;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.routine.Routine;
import backend.model.simulation.administration.Simulation;
import backend.server.EJB.dataSettings.dataSettingsEJB.ClientSettingsStatefulBean;
import backend.utils.MathUtils;

import java.util.*;

public class ClientSettings{

    public static final double PERCENTAGE_FOR_RESTAURANT = 0.148;
    private static final int INVITED_PEOPLE_MIN = 0;
    private static final int INVITED_PEOPLE_MAX = 3;
    private static final int NUM_OF_RESTAURANT_MIN = 1;
    private static final int NUM_OF_RESTAURANT_MAX = 5;
    public static final int DEATH_AGE = 75;

    private static ClientSettingsStatefulBean clientDataSettings;


    public static void init(ClientSettingsStatefulBean dataSettings) {
        clientDataSettings = dataSettings;
    }

    public static int getSalaryGroup(double salary) {
        List<Integer> salaryOptionList = new ArrayList<>(clientDataSettings.getRestaurantGroup().keySet());
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
        return clientDataSettings.getRestaurantGroup().get(salaryOption);
    }

    public static double getSalarySample() {
        double salary = Math.max(clientDataSettings.getSalaryDistribution().sample(), clientDataSettings.getMinSalary());
        if(Double.isNaN(salary)) return getSalarySample();
        return salary;
    }

    public static double getPeopleInvitedMean() {
        return MathUtils.twoNumberMean(INVITED_PEOPLE_MIN,INVITED_PEOPLE_MAX);
    }

    public static int getPeopleInvitedSample(){
        return MathUtils.random(INVITED_PEOPLE_MIN, INVITED_PEOPLE_MAX+1);
    }

    public static int getNumOfRestaurantSample(double salary, double salaryOption){
        double percentage = salary / (salaryOption + 1);
        return Math.max((int)(percentage*(NUM_OF_RESTAURANT_MAX)),NUM_OF_RESTAURANT_MIN);
    }

    public static int getNextVisitDaySample(double salary, double salaryOption) {
        double percentage = 1.0 - salary / (salaryOption + 1);
        return (int)((double)MathUtils.random(3+(int)(percentage*10),30+(int)(percentage*50)));
    }

    public static double getMinSalary(){
        return clientDataSettings.getMinSalary();
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
