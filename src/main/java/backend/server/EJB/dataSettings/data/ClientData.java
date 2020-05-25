package backend.server.EJB.dataSettings.data;

import backend.server.EJB.dataSettings.DistributionData;
import backend.server.EJB.dataSettings.MinMaxData;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Map;

public class ClientData {

    private DistributionData salary;
    private double minSalary;
    private Map<Integer,Integer> restaurantGroup;
    private MinMaxData invitedPeople;
    private MinMaxData numOfRestaurant;
    private DistributionData plateNumber;

    public ClientData(DistributionData salary, double minSalary, Map<Integer, Integer> restaurantGroup, MinMaxData invitedPeople, MinMaxData numOfRestaurant, DistributionData plateNumber) {
        this.salary = salary;
        this.minSalary = minSalary;
        this.restaurantGroup = restaurantGroup;
        this.invitedPeople = invitedPeople;
        this.numOfRestaurant = numOfRestaurant;
        this.plateNumber = plateNumber;
    }

    public double getSalaryMean() {
        return salary.getMean();
    }

    public double getSalarySd() {
        return salary.getSd();
    }

    public NormalDistribution getSalaryDistribution() {
        return new NormalDistribution(getSalaryMean(), getSalarySd());
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

    public double getPlateNumberMean(){
        return plateNumber.getMean();
    }

    public double getPlateNumberSd(){
        return plateNumber.getSd();
    }

    public NormalDistribution getPlateNumberDistribution() {
        return new NormalDistribution(getPlateNumberMean(), getPlateNumberSd());
    }
}
