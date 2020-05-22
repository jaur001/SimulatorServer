package backend.server.EJB.dataSettings.data;

import backend.server.EJB.dataSettings.MinMaxData;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Map;

public class ClientData {

    private double salaryMean;
    private double salarySd;
    private double minSalary;
    private Map<Integer,Integer> restaurantGroup;
    private MinMaxData invitedPeople;
    private MinMaxData numOfRestaurant;

    public ClientData(double salaryMean, double salarySd, double minSalary, Map<Integer, Integer> restaurantGroup, MinMaxData invitedPeople, MinMaxData numOfRestaurant) {
        this.salaryMean = salaryMean;
        this.salarySd = salarySd;
        this.minSalary = minSalary;
        this.restaurantGroup = restaurantGroup;
        this.invitedPeople = invitedPeople;
        this.numOfRestaurant = numOfRestaurant;
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

    public int getInvitedPeopleMin() {
        return invitedPeople.getMin();
    }

    public int getInvitedPeopleMax() {
        return invitedPeople.getMax();
    }

    public int getNumOfRestaurantMin() {
        return numOfRestaurant.getMin();
    }

    public int getNumOfRestaurantMax() {
        return numOfRestaurant.getMax();
    }

    public MinMaxData getInvitedPeople() {
        return invitedPeople;
    }

    public MinMaxData getNumOfRestaurant() {
        return numOfRestaurant;
    }

    public NormalDistribution getSalaryDistribution() {
        return new NormalDistribution(salaryMean, salarySd);
    }
}
