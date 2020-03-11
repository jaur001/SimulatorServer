package backend.model.utils;

import backend.model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class RoutineUtils {

    private static final int SALARY_MEAN = 1717;
    private static final NormalDistribution salaryDistribution = new NormalDistribution(SALARY_MEAN,979.28);
    private static final int MIN_SALARY = 500;
    private static final Map<Integer,Integer> restaurantGroup = new HashMap<>();

    static {
        Integer[] salaries = {1000,2000,3000,4000};
        Integer[] prices = {15,25,40,60};
        IntStream.range(0,salaries.length).boxed()
                .forEach(i -> restaurantGroup.put(salaries[i],prices[i]));
    }


    public static int getSalaryGroup(double salary) {
        List<Integer> salaryOptionList = new ArrayList<>(restaurantGroup.keySet());
        return salaryOptionList.stream()
                .filter(salaryAuxOption -> salary<=salaryAuxOption)
                .findFirst().orElse(salaryOptionList.get(salaryOptionList.size() - 1));
    }

    public static double getSalarySample() {
        double sample = salaryDistribution.sample();
        return Math.max(sample, MIN_SALARY);
    }

    public static int getNumOfRestaurantSample(){
        return MathUtils.random(1,2);
    }

    public static Restaurant[] getRestaurantOptions(double salary, List<Restaurant> restaurantList){
        int price = getSalaryGroup(salary);
        return restaurantList.stream()
                .filter(restaurant -> restaurant.getMaxPricePlate() <= price)
                .toArray(Restaurant[]::new);
    }


    public static int getNextVisitDaySample(double salary, double salaryOption) {
        return MathUtils.random(7,14);
    }
}
