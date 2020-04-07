package backend.model.simulables.person.worker.jobSearcher;

import backend.model.simulables.person.worker.JobOffer;

public class AcceptLowerOptionStrategy implements SearcherStrategy {
    @Override
    public void decide(JobOffer offer) {
        offer.acceptOffer();
    }
}
