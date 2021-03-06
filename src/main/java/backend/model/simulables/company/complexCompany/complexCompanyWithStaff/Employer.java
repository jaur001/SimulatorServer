package backend.model.simulables.company.complexCompany.complexCompanyWithStaff;

import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;


public abstract class Employer {

    protected AdministratorWithStaff administratorWithStaff;
    protected OfferManager manager;

    public Employer(AdministratorWithStaff administratorWithStaff) {
        this.administratorWithStaff = administratorWithStaff;
        this.manager = new OfferManager(administratorWithStaff);
    }


    public void checkExpiredSoonContracts() {
        administratorWithStaff.getWorkersWithExpiredSoonContracts().parallelStream()
                .filter(worker -> !WorkerSettings.isInRetireAge(worker))
                .forEach(worker -> manager.makeOffers(worker));

    }

    public void checkExpiredContracts() {
        administratorWithStaff.getWorkersWithExpiredContracts().forEach(this::checkExpiredContract);
    }

    protected void checkExpiredContract(Worker worker) {
        if(WorkerSettings.isInRetireAge(worker)) changeRetiredWorker(worker);
        else decideContract(worker);
        administratorWithStaff.removeWorker(worker);
        manager.removeOffers(worker);
    }

    protected void changeRetiredWorker(Worker worker) {
        administratorWithStaff.removeWorker(worker);
        checkStaff();
    }

    protected boolean addBestWorker(Job job) {
        Worker workerSelected = manager.searchBestWorker(job);
        if(workerSelected==null) return false;
        administratorWithStaff.addWorker(workerSelected);
        return true;
    }

    protected void decideContract(Worker worker) {
        Worker workerSelected = manager.getBestOption(worker);
        if (workerSelected.equals(worker))renovateWorker(worker);
        else changeWorker(worker,workerSelected);
    }


    protected void renovateWorker(Worker worker) {
        worker.setSalary(worker.getSalary()+ WorkerSettings.getSalaryChange()*worker.getSalary());
    }

    protected void changeWorker(Worker oldWorker, Worker workerSelected) {
        administratorWithStaff.removeWorker(oldWorker);
        administratorWithStaff.addWorker(workerSelected);
    }

    public void checkStaff() {
        while (thereIsNotEnoughWorkers()) if(addEnoughWorkers()) break;
    }

    protected boolean thereIsNotEnoughWorkers() {
        return Arrays.stream(Job.values())
                .anyMatch(this::thereIsNotEnoughWorkers);
    }

    public boolean addEnoughWorkers() {
        AtomicBoolean thereIsEnoughWorker = new AtomicBoolean(false);
        Arrays.stream(Job.values())
                .filter(this::thereIsNotEnoughWorkers)
                .forEach(job -> {
                    if(!addBestWorker(job)) thereIsEnoughWorker.set(true);
                });
        return thereIsEnoughWorker.get();
    }

    protected abstract boolean thereIsNotEnoughWorkers(Job job);
}
