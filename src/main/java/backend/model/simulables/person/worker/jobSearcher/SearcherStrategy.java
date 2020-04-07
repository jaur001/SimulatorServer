package backend.model.simulables.person.worker.jobSearcher;

import backend.model.simulables.person.worker.JobOffer;

public interface SearcherStrategy {

    void decide(JobOffer offer);
}
