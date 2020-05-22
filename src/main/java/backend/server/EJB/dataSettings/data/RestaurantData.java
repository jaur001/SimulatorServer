package backend.server.EJB.dataSettings.data;

import backend.model.simulables.person.worker.Job;
import backend.server.EJB.dataSettings.MinMaxData;

import java.util.Map;

public class RestaurantData {

    private double initialSocialCapital;
    private Map<Job, Integer> workerSalaryTable;
    private MinMaxData lengthContract;
    private double priceChange;
    private double capacity;
    private double closeLimit;

    public RestaurantData(double initialSocialCapital, Map<Job, Integer> workerSalaryTable, MinMaxData lengthContract, double priceChange, double capacity, double closeLimit) {
        this.initialSocialCapital = initialSocialCapital;
        this.workerSalaryTable = workerSalaryTable;
        this.lengthContract = lengthContract;
        this.priceChange = priceChange;
        this.capacity = capacity;
        this.closeLimit = closeLimit;
    }

    public Map<Job, Integer> getWorkerSalaryTable() {
        return workerSalaryTable;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getCloseLimit() {
        return closeLimit;
    }

    public MinMaxData getLengthContract() {
        return lengthContract;
    }
}
