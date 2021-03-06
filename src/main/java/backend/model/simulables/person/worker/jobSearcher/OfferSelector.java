package backend.model.simulables.person.worker.jobSearcher;

import backend.model.simulables.person.worker.JobOffer;

import java.util.List;

public class OfferSelector {

    private List<JobOffer> jobOfferList;
    private SelectOfferStrategy strategy;

    public OfferSelector(List<JobOffer> jobOfferList, SelectOfferStrategy strategy) {
        this.jobOfferList = jobOfferList;
        this.strategy = strategy;
    }

    public boolean searchJob() {
        if(jobOfferList.size()==0) return false;
        else if(jobOfferList.size()==1) acceptOffer(jobOfferList.get(0));
        else checkOffers();
        return true;
    }

    private void acceptOffer(JobOffer offer) {
        offer.acceptOfferWorker();
    }

    private void checkOffers() {
        strategy.decide(jobOfferList.stream().reduce(jobOfferList.get(0),this::getBetterOffer));
    }


    private JobOffer getBetterOffer(JobOffer jobOffer1, JobOffer jobOffer2) {
        return jobOffer1.getSalary()>=jobOffer2.getSalary()? jobOffer1: jobOffer2;
    }
}
