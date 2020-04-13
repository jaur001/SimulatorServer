package backend.model.simulation.settings.settingsList;

import backend.model.simulables.company.restaurant.administration.Contract;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.utils.MathUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class WorkerSettings{

    public static final int MIN_SALARY = 500;
    public static final double SALARY_CHANGE = 0.05;
    public static final double SALARY_DESIRED_CHANGE = 0.001;
    public static final int RETIRE_AGE = 65;
    public static final double PERCENTAGE_RETIREMENT = 0.60;
    private  static int[] percentages = {45,75,90,95,100};



    public static String selectJob() {
        return Job.values()[getPosition()].toString();
    }

    private static int getPosition() {
        return MathUtils.calculateProbability(percentages);
    }

    public static double reduceSalaryDesired(double salaryDesired) {
        return Math.max(MIN_SALARY,salaryDesired-WorkerSettings.SALARY_DESIRED_CHANGE *salaryDesired);
    }

    public static boolean isInRetireAge(Worker worker) {
        return worker.getAge() >= WorkerSettings.RETIRE_AGE;
    }

    public static boolean newWorker() {
        return MathUtils.random(0, Simulation.getWorkerSize()/2) < 10;
    }
}
