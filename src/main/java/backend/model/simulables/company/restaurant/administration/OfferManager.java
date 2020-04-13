package backend.model.simulables.company.restaurant.administration;

import backend.implementations.worker.GenericWorkerSearcher;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.JobOffer;
import backend.model.simulables.person.worker.Worker;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OfferManager {

    private Company company;
    private Map<Worker,List<JobOffer>> workerOffers;
    private Administrator administrator;

    public OfferManager(Company company, Administrator administrator) {
        this.company = company;
        this.administrator = administrator;
        this.workerOffers = new LinkedHashMap<>();

    }

    void makeOffers(Worker current) {
        searchBestWorkers(current).forEach(worker -> makeOffer(current,worker));
    }

    private void makeOffer(Worker current, Worker option) {
        JobOffer jobOffer = new JobOffer(company, option, option.getSalaryDesired());
        if(!workerOffers.containsKey(current)) workerOffers.put(current, new LinkedList<>());
        workerOffers.get(current).add(jobOffer);
        jobOffer.acceptOfferRestaurant();
        option.addOffer(jobOffer);
    }

    Worker getBestOption(Worker worker){
        if (thereIsNoOffers(worker)) return worker;
        List<JobOffer> jobOffers = workerOffers.get(worker);
        JobOffer option = jobOffers.parallelStream()
                .filter(JobOffer::isAccepted)
                .filter(offer -> offer.getWorker().isNotRetired())
                .reduce(jobOffers.get(0),this::getBetterOffer);
        if (option == null) return worker;
        else return option.getWorker();
    }

    private boolean thereIsNoOffers(Worker worker) {
        if(!workerOffers.containsKey(worker)) return true;
        return workerOffers.get(worker).size() == 0;
    }

    private JobOffer getBetterOffer(JobOffer offer1, JobOffer offer2) {
        return getBetterWorker(offer1.getWorker(), offer2.getWorker()).equals(offer1.getWorker()) ? offer1 : offer2;
    }

    private Worker getBetterWorker(Worker worker1, Worker worker2) {
        return administrator.getCurrentStrategy().getBetterWorker(worker1,worker2);
    }

    void removeOffers(Worker worker) {
        if(!workerOffers.containsKey(worker)) return;
        cancelOffers(worker);
        workerOffers.remove(worker);
    }

    private void cancelOffers(Worker worker) {
        workerOffers.get(worker).forEach(JobOffer::cancel);
    }

    private List<Worker> searchBestWorkers(Worker worker) {
        return new GenericWorkerSearcher(administrator.getCurrentStrategy()).searchBetterOptions(worker);
    }

    Worker searchBestWorker(Job job) {
        return new GenericWorkerSearcher(administrator.getCurrentStrategy()).searchBestOptions(job);
    }
}
