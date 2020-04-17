package backend.model.simulables.company.restaurant.administration;

import backend.implementations.workerSearcher.strategy.WorkerStrategy;
import backend.implementations.workerSearcher.strategy.strategies.complexStrategy.strategies.BestProportionScoreSalaryStrategy;
import backend.implementations.workerSearcher.strategy.strategies.complexStrategy.strategies.BestWorkerStrategy;
import backend.implementations.workerSearcher.strategy.strategies.complexStrategy.strategies.LowestSalaryStrategy;
import backend.model.simulation.administration.Simulator;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.ProductRefundTransaction;
import backend.model.simulables.company.ComplexCompany;
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

public class Administrator{

    private int tables;
    private List<Contract> contractList;
    private List<Provider> providersList;
    private FinancialData financialData;
    private ComplexCompany company;
    private WorkerStrategy currentStrategy;

    public Administrator(FinancialData financialData, int tables, ComplexCompany company) {
        this.tables = tables;
        this.company = company;
        this.providersList = new CopyOnWriteArrayList<>();
        this.contractList = new CopyOnWriteArrayList<>();
        this.financialData = financialData;
        this.currentStrategy = new BestProportionScoreSalaryStrategy();
    }

    public void addProvider(Provider provider){
        Simulator.addSimulableForCompany(company,provider);
    }

    public void removeProvider(Provider provider){
        Simulator.removeSimulableForCompany(company,provider);
    }

    public void addWorker(Worker worker){
        addContract(worker);
        Simulator.addSimulableForCompany(company,worker);
    }

    public void removeWorker(Worker worker){
        removeContract(worker);
        Simulator.removeSimulableForCompany(company,worker);
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


    public int getTables() {
        return tables;
    }

    public WorkerStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    public boolean manageFinances() {
        selectStrategy();
        return financialData.getTreasury() <= -5000;
    }

    private void selectStrategy() {
        if (isInHighLosses()) currentStrategy = new LowestSalaryStrategy();
        else if(financialData.getLastMonthLosses()*1.25 <= financialData.getLastMonthIncome()) currentStrategy = new BestWorkerStrategy();
        else currentStrategy = new BestProportionScoreSalaryStrategy();
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
