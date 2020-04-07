package backend.model.simulables.company.restaurant;

import backend.implementations.worker.GenericWorkerSearcher;
import backend.model.bill.bills.Payroll;
import backend.model.bill.bills.ProductPurchase;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.JobOffer;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.Simulation;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.model.simulation.settings.settingsList.WorkerSettings;

import java.util.*;

public class Administrator {
    private List<Provider> providersList;
    private List<Worker> workerList;
    private List<Contract> contractList;
    private FinancialData financialData;
    private Restaurant restaurant;
    private Map<Worker,List<JobOffer>> workerOffers;

    public Administrator(FinancialData financialData, Restaurant restaurant) {
        this.workerList = new LinkedList<>();
        this.contractList = new LinkedList<>();
        this.providersList = new LinkedList<>();
        this.workerOffers = new LinkedHashMap<>();
        this.financialData = financialData;
        this.restaurant = restaurant;
    }

    public void addProvider(Provider provider){
        providersList.add(provider);
        financialData.addDebt(provider.getProductPrice());
    }

    public void removeProvider(Provider provider){
        if(providersList.contains(provider)){
            providersList.remove(provider);
            financialData.removeDebt(provider.getProductPrice());
        }
    }

    public void addWorker(Worker worker, double salary){
        workerList.add(worker);
        financialData.addDebt(salary);
        contractList.add(createContract(worker));
        worker.hire(restaurant,RestaurantSettings.getSalary(Job.valueOf(worker.getJob())));
    }

    public void removeWorker(Worker worker){
        if(workerList.contains(worker)){
            workerList.remove(worker);
            financialData.removeDebt(worker.getSalary());
            worker.fire();
        }
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public List<Provider> getProvidersList() {
        return providersList;
    }



    public ProductPurchase payProvider(Provider provider) {
        ProductPurchase bill = new ProductPurchase(provider, restaurant);
        new CFDIBillGenerator().generateBill(bill);
        Bank.makeTransaction(restaurant,provider,provider.getProductPrice());
        return bill;
    }

    public Payroll payWorker(Worker worker) {
        Payroll payroll= new Payroll(worker, restaurant);
        new CFDIBillGenerator().generateBill(payroll);
        Bank.makeTransaction(restaurant,worker,worker.getSalary());
        return payroll;
    }

    public void checkExpiredSoonContracts() {
        contractList.stream().filter(Contract::isExpiredSoon)
                .forEach(contract -> makeOffers(searchBestWorker(contract.getWorker())));
    }

    private void makeOffers(List<Worker> workerList) {
        workerList.forEach(this::makeOffer);
    }

    private void makeOffer(Worker worker) {
        JobOffer jobOffer = new JobOffer(restaurant, worker, worker.getSalaryDesired());
        if(workerOffers.containsKey(worker)) workerOffers.get(worker).add(jobOffer);
        else workerOffers.put(worker, Collections.singletonList(jobOffer));

        worker.addOffer(jobOffer);
    }

    public void checkExpiredContracts() {
        contractList.stream().filter(Contract::isExpired)
                .forEach(contract -> {
                    decideContract(contract);
                    contractList.remove(contract);
                });
    }


    private void decideContract(Contract contract) {
        Worker workerSelected = getFinalOption(contract);
        if (workerSelected.equals(contract.getWorker()))renovateWorker(contract.getWorker());
        else changeWorker(contract.getWorker(),workerSelected);
    }

    private Worker getFinalOption(Contract contract){
        List<JobOffer> jobOffers = workerOffers.get(contract.getWorker());
        if(!workerOffers.containsKey(contract.getWorker())) return contract.getWorker();
        JobOffer option = jobOffers.stream()
                .filter(JobOffer::isAccepted)
                .reduce(null,this::getBetterOffer);
        if (option == null) return contract.getWorker();
        else return option.getWorker();
    }

    private JobOffer getBetterOffer(JobOffer offer1, JobOffer offer2) {
        return getSalaryPerQuality(offer1)>getSalaryPerQuality(offer2)? offer2:offer1;
    }

    private double getSalaryPerQuality(JobOffer offer) {
        return RestaurantSettings.getSalaryPerQuality(offer.getWorker());
    }

    private void changeWorker(Worker oldWorker, Worker workerSelected) {
        removeWorker(oldWorker);
        addWorker(workerSelected,getSalary(oldWorker, workerSelected));
    }

    private double getSalary(Worker oldWorker, Worker workerSelected) {
        return workerOffers.get(oldWorker).stream()
                .filter(offer -> offer.getWorker().equals(workerSelected))
                .map(JobOffer::getSalary).findFirst()
                .orElse(workerSelected.getSalary());
    }

    private List<Worker> searchBestWorker(Worker worker) {
        return new GenericWorkerSearcher(Simulation.WORKER_STRATEGY).searchBetterOptions(worker);
    }


    private void renovateWorker(Worker worker) {
        worker.setSalary(worker.getSalary()+ WorkerSettings.SALARY_CHANGE*worker.getSalary());
        contractList.add(createContract(worker));
    }

    private Contract createContract(Worker worker) {
        return new Contract(worker, RestaurantSettings.getExpireDateContract());
    }
}
