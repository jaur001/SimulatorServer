package backend.model.simulables.person.worker.jobSearcher;

import backend.model.simulables.person.worker.JobOffer;

public class AlwaysAcceptStrategy implements SelectOfferStrategy {
    @Override
    public void decide(JobOffer offer) {
        offer.acceptOfferWorker();
    }
}
