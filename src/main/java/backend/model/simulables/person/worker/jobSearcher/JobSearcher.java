package backend.model.simulables.person.worker.jobSearcher;

import backend.model.simulables.person.worker.JobOffer;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.List;

public class JobSearcher {

    private List<JobOffer> jobOfferList;
    private double salaryDesired;
    private SearcherStrategy strategy;

    public JobSearcher(List<JobOffer> jobOfferList, double salaryDesired, SearcherStrategy strategy) {
        this.jobOfferList = jobOfferList;
        this.salaryDesired = salaryDesired;
        this.strategy = strategy;
    }

    public void searchJob() {
        if(jobOfferList.size()==0) reduceSalaryDesired();
        if(jobOfferList.size()==1) acceptOffer(jobOfferList.get(0));
        else checkOffers();
    }

    private void acceptOffer(JobOffer offer) {
        offer.acceptOffer();
    }

    private void checkOffers() {
        strategy.decide(jobOfferList.stream().reduce(jobOfferList.get(0),this::getBetterOffer));
    }


    private JobOffer getBetterOffer(JobOffer jobOffer1, JobOffer jobOffer2) {
        return jobOffer1.getSalary()>=jobOffer2.getSalary()? jobOffer1: jobOffer2;
    }

    private void reduceSalaryDesired() {
        salaryDesired = Math.max(WorkerSettings.MIN_SALARY,salaryDesired-WorkerSettings.SALARY_CHANGE *salaryDesired);
    }
}
