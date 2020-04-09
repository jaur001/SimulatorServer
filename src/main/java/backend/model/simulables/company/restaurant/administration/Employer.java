package backend.model.simulables.company.restaurant.administration;

import backend.implementations.worker.GenericWorkerSearcher;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.WorkerSettings;


public class Employer {

    private Administrator administrator;
    private OfferManager manager;

    public Employer(Administrator administrator, OfferManager manager) {
        this.administrator = administrator;
        this.manager = manager;
    }



    public void checkExpiredSoonContracts() {
        administrator.getWorkersWithExpiredSoonContracts().stream()
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
        Worker workerSelected = manager.searchTheWorker(Job.valueOf(worker.getJob()));
        administrator.addWorker(workerSelected,workerSelected.getSalaryDesired());
        worker.retire();
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
}
