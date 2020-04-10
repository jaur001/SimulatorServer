package backend.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public static int calculateProbability(int[] percentages) {
        int probSelected = random(0,100);
        return IntStream.range(0,percentages.length).boxed()
                .filter(position-> percentages[position] >= probSelected).findFirst().orElse(0);
    }

}
