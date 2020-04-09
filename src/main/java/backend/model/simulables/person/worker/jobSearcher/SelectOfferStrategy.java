package backend.model.simulables.person.worker.jobSearcher;

import backend.model.simulables.person.worker.JobOffer;

public interface SelectOfferStrategy {

    void decide(JobOffer offer);
}
