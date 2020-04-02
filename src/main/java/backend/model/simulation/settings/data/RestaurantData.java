package backend.model.simulation.settings.data;

import backend.model.simulables.person.worker.Job;

import java.util.Map;

public class RestaurantData {

    private Map<Job, Integer> lengthWorkerTable;
    private Map<Job, Integer> workerSalaryTable;
    public double initialSocialCapital;

    public RestaurantData(Map<Job, Integer> lengthWorkerTable, Map<Job, Integer> workerSalaryTable, double initialSocialCapital) {
        this.lengthWorkerTable = lengthWorkerTable;
        this.workerSalaryTable = workerSalaryTable;
        this.initialSocialCapital = initialSocialCapital;
    }

    public Map<Job, Integer> getLengthWorkerTable() {
        return lengthWorkerTable;
    }

    public Map<Job, Integer> getWorkerSalaryTable() {
        return workerSalaryTable;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }
}
