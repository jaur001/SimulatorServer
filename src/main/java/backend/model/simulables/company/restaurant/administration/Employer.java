package backend.model.simulables.company.restaurant.administration;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;


public class Employer {

    private Administrator administrator;
    private OfferManager manager;

    public Employer(Administrator administrator, OfferManager manager) {
        this.administrator = administrator;
        this.manager = manager;
    }



    public void checkExpiredSoonContracts() {
        administrator.getWorkersWithExpiredSoonContracts().parallelStream()
                .filter(worker -> !WorkerSettings.isInRetireAge(worker))
                .forEach(worker -> manager.makeOffers(worker));

    }

    public void checkExpiredContracts() {
        administrator.getWorkersWithExpiredContracts().forEach(this::checkExpiredContract);
    }

    private void checkExpiredContract(Worker worker) {
        if(WorkerSettings.isInRetireAge(worker)) changeRetiredWorker(worker);
        else decideContract(worker);
        administrator.removeWorker(worker);
        manager.removeOffers(worker);
    }

    private void changeRetiredWorker(Worker worker) {
        addBestWorker(Job.valueOf(worker.getJob()));
        administrator.removeWorker(worker);

    }

    private boolean addBestWorker(Job job) {
        Worker workerSelected = manager.searchBestWorker(job);
        if(workerSelected==null) return false;
        administrator.addWorker(workerSelected);
        return true;
    }

    private void decideContract(Worker worker) {
        Worker workerSelected = manager.getBestOption(worker);
        if (workerSelected.equals(worker))renovateWorker(worker);
        else changeWorker(worker,workerSelected);
    }


    private void renovateWorker(Worker worker) {
        worker.setSalary(worker.getSalary()+ WorkerSettings.SALARY_CHANGE*worker.getSalary());
    }

    private void changeWorker(Worker oldWorker, Worker workerSelected) {
        administrator.removeWorker(oldWorker);
        administrator.addWorker(workerSelected);
    }

    public void checkStaff() {
        while (thereIsNotEnoughWorkers()) if(addEnoughWorkers()) break;
    }

    private boolean thereIsNotEnoughWorkers() {
        return Arrays.stream(Job.values())
                .anyMatch(this::thereIsNotEnoughWorkers);
    }

    public boolean addEnoughWorkers() {
        AtomicBoolean thereNotIsWorker = new AtomicBoolean(false);
        Arrays.stream(Job.values())
                .filter(this::thereIsNotEnoughWorkers)
                .forEach(job -> {
                    if(!addBestWorker(job)) thereNotIsWorker.set(true);
                });
        return thereNotIsWorker.get();
    }

    private boolean thereIsNotEnoughWorkers(Job job) {
        return RestaurantSettings.getWorkerLength(job,administrator.getTables())>administrator.getWorkerList(job).size();
    }
}
