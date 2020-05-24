package backend.model.simulables.company.complexCompanyWithStaff;

import backend.implementations.workerSearcher.strategy.WorkerStrategy;
import backend.implementations.workerSearcher.strategy.strategies.complexStrategy.strategies.BestProportionScoreSalaryStrategy;
import backend.implementations.workerSearcher.strategy.strategies.complexStrategy.strategies.BestWorkerStrategy;
import backend.implementations.workerSearcher.strategy.strategies.complexStrategy.strategies.LowestSalaryStrategy;
import backend.model.simulables.company.complexCompany.Administrator;
import backend.model.simulables.company.restaurant.administration.Contract;
import backend.model.simulation.administration.centralControl.SimulationAdministrator;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.ProductRefundTransaction;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.FinancialData;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.ProviderSettings;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.utils.MathUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class AdministratorWithStaff extends Administrator {

    private List<Contract> contractList;
    private List<Provider> providersList;
    private WorkerStrategy currentStrategy;

    public AdministratorWithStaff(FinancialData financialData, ComplexCompany company) {
        super(financialData,company);
        this.providersList = new CopyOnWriteArrayList<>();
        this.contractList = new CopyOnWriteArrayList<>();
        this.currentStrategy = new BestProportionScoreSalaryStrategy();
    }

    public void addProvider(Provider provider){
        SimulationAdministrator.addSimulableForCompany(company,provider);
    }

    public void removeProvider(Provider provider){
        SimulationAdministrator.removeSimulableForCompany(company,provider);
    }

    public void addWorker(Worker worker){
        addContract(worker);
        SimulationAdministrator.addSimulableForCompany(company,worker);
    }

    public void removeWorker(Worker worker){
        removeContract(worker);
        SimulationAdministrator.removeSimulableForCompany(company,worker);
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

    public List<Contract> getContractList() {
        return contractList;
    }

    public int getContractsSize() {
        return contractList.size();
    }

    public List<Worker> getWorkerList() {
        return contractList.stream()
                .map(Contract::getWorker)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<Worker> getWorkerList(Job job) {
        return getWorkerList().stream()
                .filter(worker -> worker.getJob().equals(job.toString()))
                .collect(Collectors.toList());
    }

    public List<Provider> getProvidersList() {
        return providersList;
    }

    public WorkerStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    public boolean manageFinances() {
        selectStrategy();
        return financialData.getTreasury() <= RestaurantSettings.getCloseLimit();
    }

    private void selectStrategy() {
        if (isInHighLosses()) currentStrategy = new LowestSalaryStrategy();
        else if(isInHighBenefits()) currentStrategy = new BestWorkerStrategy();
        else currentStrategy = new BestProportionScoreSalaryStrategy();
    }

    private boolean isInHighBenefits() {
        return financialData.getLastMonthLosses()* RestaurantSettings.FINANCIAL_DIFFERENCE_PERCENTAGE <= financialData.getLastMonthIncome();
    }

    private boolean isInHighLosses() {
        return financialData.getLastMonthLosses() >= financialData.getLastMonthIncome()* RestaurantSettings.FINANCIAL_DIFFERENCE_PERCENTAGE;
    }

    public void checkProducts() {
        if(providersList.size()==0) return;
        if(!ProviderSettings.isBadProduct()) return;
        Provider provider = providersList.get(MathUtils.random(0,providersList.size()));
        Bank.makeTransaction(new ProductRefundTransaction(provider,company,provider.getPrice()/30));
    }

}
