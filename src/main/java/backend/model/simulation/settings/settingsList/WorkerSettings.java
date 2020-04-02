package backend.model.simulation.settings.settingsList;

import backend.model.simulables.person.worker.Job;
import backend.utils.MathUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class WorkerSettings{

    private static final int WORKER_SPACE = 15000;
    private static final Map<Job,Integer> percentageOfWorkers = new LinkedHashMap<>();
    private  static int[] percentages = {45,75,90,95,100};

    static {
        IntStream.range(0,Job.values().length).boxed()
                .forEach(position -> percentageOfWorkers.put(Job.values()[position],percentages[position]));
    }


    public static int getSpaceForWorkers(){
        return WORKER_SPACE;
    }

    public static String selectJob() {
        return Job.values()[getPosition()].toString();
    }

    private static int getPosition() {
        return MathUtils.calculateProbability(percentages);
    }
}
