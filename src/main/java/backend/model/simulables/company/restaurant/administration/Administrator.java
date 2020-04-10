package backend.model.simulables.company.restaurant.administration;

import backend.model.bill.bills.Payroll;
import backend.model.bill.bills.ProductPurchase;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.provider.Product;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Administrator {

    private List<Contract> contractList;
    private List<Provider> providersList;
    private FinancialData financialData;
    private Restaurant restaurant;

    public Administrator(FinancialData financialData, Restaurant restaurant) {
        this.restaurant = restaurant;
        this.providersList = new LinkedList<>();
        this.contractList = new LinkedList<>();
        this.financialData = financialData;
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
        worker.hire(restaurant,RestaurantSettings.getSalary(Job.valueOf(worker.getJob())));
        addContract(worker);
        financialData.addDebt(salary);
    }

    public void removeWorker(Worker worker){
        if(getWorkerList().contains(worker)){
            worker.fire();
            removeContract(worker);
            financialData.removeDebt(worker.getSalary());
        }
    }

    private void addContract(Worker worker) {
        contractList.add(createContract(worker));
    }

    private void removeContract(Worker worker) {
        Contract contract = getContract(worker);
        if(contract!=null)contractList.remove(contract);
    }

    private Contract getContract(Worker worker) {
        return contractList.stream().filter(contract -> contract.getWorker().equals(worker)).findFirst().orElse(null);
    }

    private Contract createContract(Worker worker) {
        return new Contract(worker, RestaurantSettings.getExpireDateContract());
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


    public List<Worker> getWorkersWithExpiredSoonContracts(){
        return contractList.stream()
                .filter(Contract::isExpiredSoon)
                .map(Contract::getWorker)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<Worker> getWorkersWithExpiredContracts(){
        return contractList.stream()
                .filter(Contract::isExpired)
                .map(Contract::getWorker)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public int getContractsSize() {
        return contractList.size();
    }

    public List<Worker> getWorkerList() {
        return contractList.stream()
                .map(Contract::getWorker)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    public List<Provider> getProvidersList() {
        return providersList;
    }

    public Provider getProvider(Product product){
        return providersList.stream()
                .filter(provider -> provider.getProduct().equals(product))
                .findFirst().orElse(null);
    }
}
