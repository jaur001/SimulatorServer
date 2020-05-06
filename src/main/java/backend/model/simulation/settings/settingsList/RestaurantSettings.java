package backend.model.simulation.settings.settingsList;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.server.EJB.dataSettings.Adjustable;
import backend.server.EJB.dataSettings.SettingsData;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.EJB.dataSettings.dataSettingsEJB.RestaurantSettingsStatefulBean;
import backend.utils.MathUtils;
import org.apache.commons.math3.distribution.BetaDistribution;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RestaurantSettings{

    private static final int ALPHA = 2;
    private static final int BETA = 4;
    private static final BetaDistribution numTablesDistribution = new BetaDistribution(ALPHA, BETA);
    private static final int MIN_TABLES = 4;
    private static final int MAX_TABLES = 50;
    private static final int WORKERS_MIN = 1;
    private static final Map<Job, Integer> lengthWorkerTable = new HashMap<>();

    public static final int EATINGS_PER_TABLE = 6;
    public static final int MIN_LENGTH_CONTRACT = 90;
    public static final int MAX_LENGTH_CONTRACT = 360;
    public static final double PRICE_CHANGE = 0.02;
    public static final double FINANCIAL_DIFFERENCE_PERCENTAGE = 1.25;

    private static RestaurantSettingsStatefulBean restaurantDataSettings;

    static {
        getNumberOfWorkers();
    }

    private static void getNumberOfWorkers(){
        Integer[] lengthPerTenTable = {3,2,1,-1,-1};
        IntStream.range(0,lengthPerTenTable.length).boxed()
                .forEach(i -> lengthWorkerTable.put(Job.values()[i],lengthPerTenTable[i]));

    }

    public static void init(RestaurantSettingsStatefulBean dataSettings) {
        restaurantDataSettings = dataSettings;
    }



    public static double getSalaryPerQuality(Worker worker) {
        return (worker.getQuality().getScore()/worker.getSalaryDesired())*10000;
    }

    public static double getInitialSocialCapital() {
        return restaurantDataSettings.getInitialSocialCapital();
    }

    public static int getWorkerLength(Job job, int numTables) {
        if(job.equals(Job.Chef) || job.equals(Job.Maitre)) return numTables>=30? 1: 0;
        return (int)Math.max(Math.round(((lengthWorkerTable.get(job)/10.0))*numTables), WORKERS_MIN);
    }

    public static int getSalary(Job job){
        return restaurantDataSettings.getWorkerSalaryTable().get(job);
    }

    public static int getNumTablesMean(){
        return (int)MathUtils.twoNumberMean(MIN_TABLES,MAX_TABLES);
    }

    public static int getClientLimitMean(){
        return getNumTablesMean()*EATINGS_PER_TABLE;
    }

    public static int getNumTablesSample(){
        return (int)Math.max(MIN_TABLES,Math.round(numTablesDistribution.sample()* MAX_TABLES));
    }

    public static Date getExpireDateContract(){
        Date date = (Date) TimeLine.getDate().clone();
        date.setDate(date.getDate()+getContractLength());
        return date;
    }

    public static int getContractLength() {
        return MathUtils.random(MIN_LENGTH_CONTRACT, MAX_LENGTH_CONTRACT);
    }

    public static boolean newRestaurant() {
        return MathUtils.calculateProbability((int)WorkerSettings.getUnemployedWorkersPercentage());
    }
}
