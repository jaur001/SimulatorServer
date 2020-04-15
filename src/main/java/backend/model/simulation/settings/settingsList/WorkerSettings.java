package backend.model.simulation.settings.settingsList;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.Simulation;
import backend.utils.MathUtils;

public class WorkerSettings{

    public static final int MIN_SALARY = 500;
    public static final double SALARY_CHANGE = 0.05;
    public static final double SALARY_DESIRED_CHANGE = 0.001;
    public static final int RETIRE_AGE = 65;
    public static final double PERCENTAGE_RETIREMENT = 0.60;

    public static double reduceSalaryDesired(double salaryDesired) {
        return Math.max(MIN_SALARY,salaryDesired-WorkerSettings.SALARY_DESIRED_CHANGE *salaryDesired);
    }

    public static boolean isInRetireAge(Worker worker) {
        return worker.getAge() >= WorkerSettings.RETIRE_AGE;
    }

    public static boolean newWorker() {
        return MathUtils.calculateProbability(100- (int)getUnemployedWorkersPercentage());
    }

    public static double getUnemployedWorkersPercentage(){
        return ((double)Simulation.getUnemployedWorkers().size()/(double)(1+Simulation.getWorkerSize()))*100.0;
    }

    public static double getUnemployedWorkersPerJob(Job job){
        return ((double)Simulation.getUnemployedWorkers(job).size()/(double)(1+Simulation.getWorkerList(job).size()))*100.0;
    }


}
