package backend.model.simulables.company.complexCompany.complexCompanyWithStaff;

import backend.implementations.workerSearcher.GenericWorkerSearcher;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.JobOffer;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.centralControl.SimulationAdministrator;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OfferManager {

    private Map<Worker,List<JobOffer>> workerOffers;
    private AdministratorWithStaff administratorWithStaff;

    public OfferManager(AdministratorWithStaff administratorWithStaff) {
        this.administratorWithStaff = administratorWithStaff;
        this.workerOffers = new LinkedHashMap<>();

    }

    void makeOffers(Worker current) {
        searchBetterWorkers(current).forEach(worker -> makeOffer(current,worker));
    }

    private void makeOffer(Worker current, Worker option) {
        JobOffer jobOffer = new JobOffer(administratorWithStaff.getCompany(), option, option.getSalaryDesired());
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
                .filter(offer -> SimulationAdministrator.isNotAlreadyHired(offer.getWorker()))
                .filter(offer -> SimulationAdministrator.isNotAlreadyRetired(offer.getWorker()))
                .filter(offer -> !offer.getWorker().isWorking())
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
        return administratorWithStaff.getCurrentStrategy().getBetterWorker(worker1,worker2);
    }

    void removeOffers(Worker worker) {
        if(!workerOffers.containsKey(worker)) return;
        cancelOffers(worker);
        workerOffers.remove(worker);
    }

    private void cancelOffers(Worker worker) {
        workerOffers.get(worker).forEach(JobOffer::cancel);
    }

    private List<Worker> searchBetterWorkers(Worker worker) {
        return new GenericWorkerSearcher(administratorWithStaff.getCurrentStrategy()).searchBetterOptions(worker);
    }

    Worker searchBestWorker(Job job) {
        return new GenericWorkerSearcher(administratorWithStaff.getCurrentStrategy()).searchBestOptions(job);
    }
}
