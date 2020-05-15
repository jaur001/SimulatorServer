package backend.server.EJB.dataSettings.data;

import java.util.Map;

public class ClientData {

    private double salaryMean;
    private double salarySd;
    private double minSalary;
    private Map<Integer,Integer> restaurantGroup;

    public ClientData(double salaryMean, double salarySd, double minSalary, Map<Integer, Integer> restaurantGroup) {
        this.salaryMean = salaryMean;
        this.salarySd = salarySd;
        this.minSalary = minSalary;
        this.restaurantGroup = restaurantGroup;
    }

    public double getSalaryMean() {
        return salaryMean;
    }

    public double getSalarySd() {
        return salarySd;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public Map<Integer, Integer> getRestaurantGroup() {
        return restaurantGroup;
    }

    public void setSalaryMean(double salaryMean) {
        this.salaryMean = salaryMean;
    }

    public void setSalarySd(double salarySd) {
        this.salarySd = salarySd;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public void setRestaurantGroup(Map<Integer, Integer> restaurantGroup) {
        this.restaurantGroup = restaurantGroup;
    }
}
