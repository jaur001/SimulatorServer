package backend.model.simulation.settings.settingsList;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.server.EJB.dataSettings.data.WorkerData;
import backend.utils.MathUtils;

public class WorkerSettings{

    public static WorkerData getWorkerDataSettings(){
        return SimulationDataController.getWorkerData();
    }

    public static double reduceSalaryDesired(double salaryDesired) {
        return Math.max(getWorkerDataSettings().getMinSalary(),salaryDesired-WorkerSettings.getWorkerDataSettings().getSalaryDesiredChange() *salaryDesired);
    }

    public static boolean isInRetireAge(Worker worker) {
        return worker.getAge() >= WorkerSettings.getWorkerDataSettings().getRetireAge();
    }

    public static double getSalaryChange(){
        return getWorkerDataSettings().getSalaryChange();
    }

    public static double getPercentageRetirement() {
        return getWorkerDataSettings().getPercentageRetirement();
    }

    public static boolean newWorker() {
        return MathUtils.calculateProbability((int)((100- getUnemployedWorkersPercentage())*getWorkerProbability()));
    }

    private static double getWorkerProbability() {
        return 1;
    }

    public static double getUnemployedWorkersPercentage(){
        return ((double)Simulation.getUnemployedWorkers().size()/(double)(1+Simulation.getWorkerSize()))*100.0;
    }

    public static double getUnemployedWorkersPerJob(Job job){
        return ((double)Simulation.getUnemployedWorkers(job).size()/(double)(1+Simulation.getWorkerList(job).size()))*100.0;
    }
}
