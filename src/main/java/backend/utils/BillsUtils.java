package backend.utils;

import backend.model.simulables.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.HashMap;
import java.util.Map;

public class BillsUtils {

    private static final int NUMBER_PEOPLE_MEAN = 4;
    private static final int PLATE_NUMBER_MEAN = 2;
    private static final NormalDistribution plateNumberDistribution = new NormalDistribution(PLATE_NUMBER_MEAN,0.7);
    private static final Map<String,String> conceptsTable = new HashMap<>();

    static {
        conceptsTable.put("EatingSale","Bill of a eating");
        conceptsTable.put("ProductPurchase","Purchase of a product for the restaurant");
        conceptsTable.put("Payroll","Payroll o a worker");
    }

    public static double getPlateNumberMean(){
        return plateNumberDistribution.getMean();
    }

    public static int getNumberPeopleMean(){
        return NUMBER_PEOPLE_MEAN;
    }

    public static String getConcept(String billType){
        return conceptsTable.get(billType);
    }

    public static double calculatePrice(Restaurant restaurant, int peopleInvited){
        double mean = MathUtils.twoNumberMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return getPriceOfPlate(restaurant, mean) * getPlateNumberSample() * (peopleInvited+1);

    }

    private static int getPlateNumberSample() {
        double sample = Math.round(Math.abs(plateNumberDistribution.sample()));
        return (int)(sample<1? 1: sample);
    }

    private static double getPriceOfPlate(Restaurant restaurant, double mean) {
        return platePriceIsConstant(restaurant) ? mean :
                getPlatePriceSample(restaurant);
    }

    private static boolean platePriceIsConstant(Restaurant restaurant) {
        return restaurant.getMaxPricePlate()==restaurant.getMinPricePlate();
    }

    private static double getPlatePriceSample(Restaurant restaurant){
        double mean = MathUtils.twoNumberMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return Math.abs(new NormalDistribution(mean,restaurant.getMaxPricePlate()-mean).sample());
    }
}
