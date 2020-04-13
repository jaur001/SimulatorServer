package backend.model.simulables.company.restaurant.administration;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.Arrays;


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
        administrator.retireWorker(worker);

    }

    private void addBestWorker(Job job) {
        Worker workerSelected = manager.searchBestWorker(job);
        if(workerSelected!=null)
            administrator.addWorker(workerSelected,workerSelected.getSalaryDesired());
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
        manager.deleteOtherOffersSelectedWorker(workerSelected);
        administrator.removeWorker(oldWorker);
        administrator.addWorker(workerSelected,manager.getSalary(oldWorker, workerSelected));
    }

    public void checkStaff() {
        Arrays.stream(Job.values())
                .filter(this::thereIsEnoughWorkers)
                .forEach(this::addBestWorker);
    }

    private boolean thereIsEnoughWorkers(Job job) {
        return RestaurantSettings.getWorkerLength(job,administrator.getTables())>administrator.getWorkerList().size();
    }
}
