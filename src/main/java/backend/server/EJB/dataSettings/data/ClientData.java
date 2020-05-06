package backend.server.EJB.dataSettings.data;

import java.util.Map;

public class ClientData {

    private int salaryMean;
    private double salarySd;
    private int minSalary;
    private Map<Integer,Integer> restaurantGroup;

    public ClientData(int salaryMean, double salarySd, int minSalary, Map<Integer, Integer> restaurantGroup) {
        this.salaryMean = salaryMean;
        this.salarySd = salarySd;
        this.minSalary = minSalary;
        this.restaurantGroup = restaurantGroup;
    }

    public int getSalaryMean() {
        return salaryMean;
    }

    public double getSalarySd() {
        return salarySd;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public Map<Integer, Integer> getRestaurantGroup() {
        return restaurantGroup;
    }
}
