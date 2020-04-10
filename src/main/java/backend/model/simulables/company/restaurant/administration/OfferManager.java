package backend.model.simulables.company.restaurant.administration;

import backend.implementations.worker.GenericWorkerSearcher;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.JobOffer;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.RestaurantSettings;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OfferManager {

    private Restaurant restaurant;
    private Map<Worker,List<JobOffer>> workerOffers;

    public OfferManager(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.workerOffers = new LinkedHashMap<>();
    }

    void makeOffers(Worker current) {
        searchBestWorkers(current).forEach(worker -> makeOffer(current,worker));
    }

    private void makeOffer(Worker current, Worker option) {
        JobOffer jobOffer = new JobOffer(restaurant, option, option.getSalaryDesired());
        if(!workerOffers.containsKey(current)) workerOffers.put(current, new LinkedList<>());
        workerOffers.get(current).add(jobOffer);
        jobOffer.acceptOfferRestaurant();
        option.addOffer(jobOffer);
    }

    Worker getBestOption(Worker worker){
        if (checkOffers(worker)) return worker;
        List<JobOffer> jobOffers = workerOffers.get(worker);
        JobOffer option = jobOffers.parallelStream()
                .filter(JobOffer::isAccepted)
                .reduce(jobOffers.get(0),this::getBetterOffer);
        if (option == null) return worker;
        else return option.getWorker();
    }

    private boolean checkOffers(Worker worker) {
        if(!workerOffers.containsKey(worker)) return true;
        return workerOffers.get(worker).size() == 0;
    }

    private JobOffer getBetterOffer(JobOffer offer1, JobOffer offer2) {
        return getSalaryPerQuality(offer1)>=getSalaryPerQuality(offer2)? offer1:offer2;
    }

    private double getSalaryPerQuality(JobOffer offer) {
        return RestaurantSettings.getSalaryPerQuality(offer.getWorker());
    }

    void deleteOtherOffersSelectedWorker(Worker workerSelected) {
        workerOffers.keySet().forEach(worker -> {
            List<JobOffer> offerList = new LinkedList<>(workerOffers.get(worker));
            offerList.parallelStream()
                    .filter(offer -> offer.getWorker().equals(workerSelected))
                    .forEach(offer -> workerOffers.get(worker).remove(offer));
        });
    }

    double getSalary(Worker oldWorker, Worker workerSelected) {
        return workerOffers.get(oldWorker).stream()
                .filter(offer -> offer.getWorker().equals(workerSelected))
                .map(JobOffer::getSalary).findFirst()
                .orElse(workerSelected.getSalary());
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
        return new GenericWorkerSearcher(Simulation.WORKER_STRATEGY).searchBetterOptions(worker);
    }

    Worker searchTheWorker(Job job) {
        return new GenericWorkerSearcher(Simulation.WORKER_STRATEGY).searchBestOptions(job);
    }
}
