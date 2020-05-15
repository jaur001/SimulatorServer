package backend.server.EJB.dataSettings.data;

import backend.model.simulables.person.worker.Job;

import java.util.Map;

public class RestaurantData {

    private double initialSocialCapital;
    private Map<Job, Integer> workerSalaryTable;

    public RestaurantData(double initialSocialCapital, Map<Job, Integer> workerSalaryTable) {
        this.workerSalaryTable = workerSalaryTable;
        this.initialSocialCapital = initialSocialCapital;
    }

    public Map<Job, Integer> getWorkerSalaryTable() {
        return workerSalaryTable;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public void setInitialSocialCapital(double initialSocialCapital) {
        this.initialSocialCapital = initialSocialCapital;
    }

    public void setWorkerSalaryTable(Map<Job, Integer> workerSalaryTable) {
        this.workerSalaryTable = workerSalaryTable;
    }
}
