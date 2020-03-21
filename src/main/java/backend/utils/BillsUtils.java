package backend.utils;

import backend.model.simulables.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;

public class BillsUtils {

    private static final int NUMBER_PEOPLE_MEAN = 4;
    private static final int PLATE_NUMBER_MEAN = 2;
    private static final NormalDistribution plateNumberDistribution = new NormalDistribution(PLATE_NUMBER_MEAN,0.7);

    public static int getPlateNumberSample() {
        double sample = Math.round(Math.abs(plateNumberDistribution.sample()));
        return (int)(sample<1? 1: sample);
    }

    public static double getPlateNumberMean(){
        return plateNumberDistribution.getMean();
    }

    public static int getNumberPeopleMean(){
        return NUMBER_PEOPLE_MEAN;
    }


    public static double getPriceApproximation(Restaurant restaurant, int plateNumber, int invitedPeople){
        double mean = MathUtils.twoNumberMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return (platePriceIsConstant(restaurant) ? mean :
                getPlatePriceSample(restaurant)) * plateNumber * invitedPeople;

    }

    private static double getPlatePriceSample(Restaurant restaurant){
        double mean = MathUtils.twoNumberMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return Math.abs(new NormalDistribution(mean,restaurant.getMaxPricePlate()-mean).sample());
    }

    private static boolean platePriceIsConstant(Restaurant restaurant) {
        return restaurant.getMaxPricePlate()==restaurant.getMinPricePlate();
    }
}
