package backend.utils;

import backend.model.simulables.restaurant.worker.Job;
import org.apache.commons.math3.distribution.BetaDistribution;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RestaurantUtils {

    private static final int MIN = 4;
    private static final int MAX = 40;
    private static final int ALPHA = 2;
    private static final int BETA = 4;
    private static final int WORKERS_MIN = 1;
    public static double intialSocialCapital = 10000;
    private static BetaDistribution numTablesDistribution = new BetaDistribution(ALPHA, BETA);
    private static final Map<Job, Integer> lengthWorkerTable = new HashMap<>();
    private static final Map<Job, Integer> workerSalaryTable = new HashMap<>();

    static {
        Job[] jobs = {Job.Waiter,Job.KitchenHelper,Job.Cooker,Job.Maitre,Job.Chef,Job.Receptionist};
        Integer[] lengthPerTenTable = {3,2,1,-1,-1,-1};
        IntStream.range(0,lengthPerTenTable.length).boxed()
                .forEach(i -> lengthWorkerTable.put(jobs[i],lengthPerTenTable[i]));
        Integer[] salaries = {500,500,500,500,500,500};
        IntStream.range(0,salaries.length).boxed()
                .forEach(i -> RestaurantUtils.workerSalaryTable.put(jobs[i],salaries[i]));
    }


    public static int getWorkerLengthGroup(Job job, int numTables) {
        return (int)Math.max(Math.round(((lengthWorkerTable.get(job)/10.0))*numTables), WORKERS_MIN);
    }

    public static int getWorkerSalaryGroup(Job job){
        return workerSalaryTable.get(job);
    }



    public static int getNumTablesSample(){
        return (int)Math.max(MIN,Math.round(numTablesDistribution.sample()* MAX));
    }
}
