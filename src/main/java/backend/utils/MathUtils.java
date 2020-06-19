package backend.utils;

import java.util.Random;
import java.util.stream.IntStream;

public class MathUtils {

    private static final Random RANDOM = new Random();

    public static int random(int min, int max) {
        return RANDOM.nextInt(max-min)+min;
    }

    public static int random(MinMaxData minMaxData) {
        return random(minMaxData.getMin(),minMaxData.getMax());
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

    public static double twoNumberMean(MinMaxData data){
        return twoNumberMean(data.getMin(),data.getMax());
    }

    public static int calculateProbabilities(Integer[] percentages) {
        int probSelected = random(0,percentages[percentages.length-1]);
        return IntStream.range(0,percentages.length).boxed()
                .filter(position-> percentages[position] >= probSelected).findFirst().orElse(0);
    }

    public static boolean calculateProbability(int number) {
        return random(0,100)<number;
    }
}
