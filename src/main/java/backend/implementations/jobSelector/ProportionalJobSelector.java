package backend.implementations.jobSelector;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.JobSelector;
import backend.utils.MathUtils;

public class ProportionalJobSelector implements JobSelector {

    private  static final Integer[] percentages = {50,80,95,97,100};

    @Override
    public Job selectJob() {
        return Job.values()[getPosition()];
    }

    private int getPosition() {
        return MathUtils.calculateProbabilities(percentages);
    }
}
