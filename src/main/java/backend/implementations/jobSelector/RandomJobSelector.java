package backend.implementations.jobSelector;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.JobSelector;
import backend.utils.MathUtils;

public class RandomJobSelector implements JobSelector {
    @Override
    public Job selectJob() {
        return Job.values()[MathUtils.random(0,Job.values().length)];
    }
}
