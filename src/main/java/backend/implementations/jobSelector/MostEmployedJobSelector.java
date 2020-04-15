package backend.implementations.jobSelector;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.JobSelector;
import backend.model.simulation.settings.settingsList.WorkerSettings;
import backend.utils.MathUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MostEmployedJobSelector implements JobSelector {
    @Override
    public Job selectJob() {
        return Job.values()[getPosition()];
    }

    private int getPosition() {
        return MathUtils.calculateProbabilities(getProbabilities());
    }

    private Integer[] getProbabilities() {
        AtomicInteger lastProbability = new AtomicInteger(0);
        return Arrays.stream(Job.values())
                .map(job -> getProbability(lastProbability, job))
                .toArray(Integer[]::new);
    }

    private int getProbability(AtomicInteger lastProbability, Job job) {
        int probability = (int)calculateProbability(job);
        return probability +lastProbability.getAndAdd(probability);
    }

    private double calculateProbability(Job job) {
        return 100-WorkerSettings.getUnemployedWorkersPerJob(job);
    }

}
