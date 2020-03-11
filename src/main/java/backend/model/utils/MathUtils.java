package backend.model.utils;

import java.util.Random;

public class MathUtils {

    private static final Random RANDOM = new Random();

    public static int random(int min, int max) {
        return RANDOM.nextInt(max-min)+min;
    }

    public static double mean(double[] data){
        double sum = 0.0;
        int length = data.length;
        for(double num : data) {
            sum += num;
        }
        return sum/length;
    }

    public static double standardDeviation(double[] data){
        double standardDeviation = 0.0;
        double mean = mean(data);
        for(double num: data) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/data.length);
    }

    public static double twoNumberMean(double min, double max){
        return mean(new double[]{min,max});
    }
}
