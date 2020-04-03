package backend.model.simulation.settings.settingsList;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.model.simulables.person.worker.Job;
import backend.model.simulation.settings.Adjustable;
import backend.model.simulation.settings.SettingsData;
import org.apache.commons.math3.distribution.BetaDistribution;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RestaurantSettings implements Adjustable {

    private static final int ALPHA = 2;
    private static final int BETA = 4;
    private static final BetaDistribution numTablesDistribution = new BetaDistribution(ALPHA, BETA);
    private static final int MIN_TABLES = 4;
    private static final int MAX_TABLES = 40;
    private static final int WORKERS_MIN = 1;


    private static Map<Job, Integer> lengthWorkerTable = new HashMap<>();
    private static Map<Job, Integer> workerSalaryTable = new HashMap<>();
    private static double initialSocialCapital = 10000;

    static {
        getDefaultSettings();
    }

    private static void getDefaultSettings() {
        initialSocialCapital = 10000;
        getNumberOfWorkers();
        getWorkersSalary();
    }

    private static void getNumberOfWorkers(){
        Integer[] lengthPerTenTable = {3,2,1,-1,-1};
        IntStream.range(0,lengthPerTenTable.length).boxed()
                .forEach(i -> lengthWorkerTable.put(Job.values()[i],lengthPerTenTable[i]));

    }

    private static void getWorkersSalary(){
        Integer[] salaries = {500,500,500,500,500};
        IntStream.range(0,salaries.length).boxed()
                .forEach(i -> workerSalaryTable.put(Job.values()[i],salaries[i]));
    }

    @Override
    public void init(SettingsData data) {
        initialSocialCapital = data.getRestaurantData().getInitialSocialCapital();
        lengthWorkerTable = data.getRestaurantData().getLengthWorkerTable();
        workerSalaryTable = data.getRestaurantData().getWorkerSalaryTable();
    }

    @Override
    public void setDefault() {
        getDefaultSettings();
    }


    public static double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public static int getWorkerLength(Job job, int numTables) {
        return (int)Math.max(Math.round(((lengthWorkerTable.get(job)/10.0))*numTables), WORKERS_MIN);
    }

    public static int getSalary(Job job){
        return workerSalaryTable.get(job);
    }

    public static int getNumTablesSample(){
        return (int)Math.max(MIN_TABLES,Math.round(numTablesDistribution.sample()* MAX_TABLES));
    }

    public static int getLimit() throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().readCount("Restaurant");
    }
}
